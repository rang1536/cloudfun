package com.cloudfun.www.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudfun.www.login.service.LoginService;
import com.cloudfun.www.util.UtilFile;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class LoginRestController {
	
	@Value("#{globals['google.client.id']}") 
    private String googleClientId;
	@Value("#{globals['google.client.pw']}") 
    private String googleClientPw;
	@Value("#{globals['google.redirect.url']}") 
    private String googleRedirectUrl;
	 
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UtilFile utilFile;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/api/v1/oauth2/google", method = RequestMethod.POST)
	public String loginUrlGoogle(){
		
        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
                 + "&redirect_uri="+googleRedirectUrl+"&response_type=code&scope=email%20profile%20openid&access_type=offline";
        //return "redirect:"+ reqUrl;
        return reqUrl;
    }
	
	
	
	/**
	 * 로그아웃처리 세션 초기화.
	 */
	@RequestMapping(value="/api/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request){
		
		HttpSession  session = request.getSession();
		
		// type은 버리지않음.
		String type = (String)session.getAttribute("type");
		
		session.invalidate();

		
        String reqUrl = "/";
        //return "redirect:"+ reqUrl;
        return reqUrl;
    }
	
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/api/join", method = RequestMethod.POST)
	public HashMap<String, String> joinr(@RequestBody HashMap<String, String> map, HttpServletRequest request){
		
		HttpSession session = request.getSession();

		HashMap<String, String> resultMap = new HashMap<String, String>();
		//resultMap.put("test", "testValue");
		  
		  
		// session 정보
		String sessionEmail = (String)session.getAttribute("email");
		String sessionName = (String)session.getAttribute("name");
		String sessionType = (String)session.getAttribute("type");
		  
		// form 정보
		String email = map.get("email");
		String name = map.get("name");
		String password = map.get("password");
		String birth = map.get("birth");
		String nation = map.get("nation");
		
		  
		
		// 구글로그인 정보와 다르면 return
		if(!sessionEmail.equals(email)) {
			resultMap.put("error", "error");
			return resultMap;
		}  
		if(!sessionName.equals(name)) {
			resultMap.put("error", "error");
			return resultMap;
		}
		
		  
		// 1. 회원인지 확인한다.(유형 확인.)
		Map<String, String> objParam = new HashMap<String,String>();
		objParam.put("email", email);
		objParam.put("name", name);
		  
		// 도메인에다라 타입구분.(글/그림/만화/음악/3D Model 5개)
		/*
		 * 글 : text
		 * 그림 : picture
		 * 만화 : comic
		 * 음악 : music
		 * 3D Model : model
		* */ 
		objParam.put("type", sessionType);
		
		objParam.put("password", password);
		objParam.put("birth", birth);
		objParam.put("nation", nation);
		  
		String memberId =  loginService.isLogin(objParam);
		
		
		if(memberId !=null) {
			// 이미회원임. 로그인 되었어야한다.
//		 	2. 회원인경우 메인 페이지 이동
			resultMap.put("url", "/");
			session.setAttribute("memberId", memberId);
			return resultMap;
		  		
		}else{
			
			// 	3. 회원이 아닌경우 회원가입..
			//	 	3-1 회원테이블 merge
			//    	3-2 회원 상세 테이블 insert
			memberId = loginService.join(objParam);
			session.setAttribute("memberId", memberId);
			
		}
  
		resultMap.put("url", "/");
		return resultMap;

    }
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	/*@RequestMapping(value="/api/saveMember", method = RequestMethod.POST)
	public HashMap<String, String> saveMember(@RequestBody HashMap<String, String> map, HttpServletRequest request){
		
		HttpSession session = request.getSession();

		HashMap<String, String> resultMap = new HashMap<String, String>();
		HashMap<String,String> obj = new HashMap<String, String>();
		//resultMap.put("test", "testValue");
		  
		  
		// session 정보
		String memberId = (String)session.getAttribute("memberId");
		String type = (String)session.getAttribute("type");
		  
		// form 정보
		String email = map.get("email");
		String name = map.get("name");
		String nickName = map.get("nickName");
		String password = map.get("password");
		String birth = map.get("birth");
		
		obj.put("nickName", nickName);
		obj.put("birth", birth);
		
		obj.put("memberId", memberId);
		obj.put("type", type);
		
		
		loginService.saveMemberDetail(obj);
		
		return resultMap;

    }
	*/
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/api/saveMember", method = RequestMethod.POST)
	public HashMap<String, String> saveMember(
			//@RequestPart("uploadFile") MultipartFile[] uploadFile
			//, @RequestPart("mainImg") MultipartFile[] mainImg
			@RequestPart(value="passPortFile", required = false) MultipartFile[] desImg
			, @RequestParam("jsonStr") String jsonStr
			,  HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{
		
		//json to map
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,String> map = objectMapper.readValue(jsonStr, Map.class);
		
		
		HttpSession session = request.getSession();

		HashMap<String, String> resultMap = new HashMap<String, String>();
		HashMap<String,String> obj = new HashMap<String, String>();
		//resultMap.put("test", "testValue");
		
		  
		// session 정보
		String memberId = (String)session.getAttribute("memberId");
		String type = (String)session.getAttribute("type");
		  
		// form 정보
		String email = map.get("email");
		String name = map.get("name");
		String nation = map.get("nation");
		String accountNm = map.get("accountNm");
		String accountNo = map.get("accountNo");
		String passportNo = map.get("passportNo");
		
		
		obj.put("memberId", memberId);
		obj.put("type", type);
		obj.put("nation", nation);
		obj.put("accountNm", accountNm);
		obj.put("accountNo", accountNo);
		obj.put("passportNo", passportNo);
		
		if(desImg.length > 0) {
			// 파일 업로드
			obj.put("postId",memberId);
			obj.put("groupId",type);
			// 1. 기존파일 삭제
			loginService.updateOldFile(obj);
			// 2. 파일 insert
			for(MultipartFile multipartFile : desImg) {
				utilFile.uploadFile(multipartFile,obj) ;
			}
		}
		
		loginService.saveMemberDetail(obj);
		
		
		resultMap.put("url", "/");
		return resultMap;

    }
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/api/selectNation", method = RequestMethod.POST)
	public HashMap<String, Object> selectNation(@RequestBody HashMap<String, String> map, HttpServletRequest request){
		
		HttpSession session = request.getSession();

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String,String> obj = new HashMap<String, String>();
		  
		
		List<Map<String, String>> nationCdList = loginService.selectNationCd(obj);
		resultMap.put("nationCdList", nationCdList);
		
		
		return resultMap;

    }
	
	
}
