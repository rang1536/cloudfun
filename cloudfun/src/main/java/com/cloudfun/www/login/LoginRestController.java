package com.cloudfun.www.login;

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
import org.springframework.web.bind.annotation.RestController;

import com.cloudfun.www.login.service.LoginService;

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
	 * �α׾ƿ�ó�� ���� �ʱ�ȭ.
	 */
	@RequestMapping(value="/api/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request){
		
		HttpSession  session = request.getSession();
		
		// type�� ����������.
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
		  
		  
		// session ����
		String sessionEmail = (String)session.getAttribute("email");
		String sessionName = (String)session.getAttribute("name");
		String sessionType = (String)session.getAttribute("type");
		  
		// form ����
		String email = map.get("email");
		String name = map.get("name");
		String password = map.get("password");
		String birth = map.get("birth");
		String nation = map.get("nation");
		
		  
		
		// ���۷α��� ������ �ٸ��� return
		if(!sessionEmail.equals(email)) {
			resultMap.put("error", "error");
			return resultMap;
		}  
		if(!sessionName.equals(name)) {
			resultMap.put("error", "error");
			return resultMap;
		}
		
		  
		// 1. ȸ������ Ȯ���Ѵ�.(���� Ȯ��.)
		Map<String, String> objParam = new HashMap<String,String>();
		objParam.put("email", email);
		objParam.put("name", name);
		  
		// �����ο��ٶ� Ÿ�Ա���.(��/�׸�/��ȭ/����/3D Model 5��)
		/*
		 * �� : text
		 * �׸� : picture
		 * ��ȭ : comic
		 * ���� : music
		 * 3D Model : model
		* */ 
		objParam.put("type", sessionType);
		
		objParam.put("password", password);
		objParam.put("birth", birth);
		objParam.put("nation", nation);
		  
		String memberId =  loginService.isLogin(objParam);
		
		
		if(memberId !=null) {
			// �̹�ȸ����. �α��� �Ǿ�����Ѵ�.
//		 	2. ȸ���ΰ�� ���� ������ �̵�
			resultMap.put("url", "/");
			session.setAttribute("memberId", memberId);
			return resultMap;
		  		
		}else{
			
			// 	3. ȸ���� �ƴѰ�� ȸ������..
			//	 	3-1 ȸ�����̺� merge
			//    	3-2 ȸ�� �� ���̺� insert
			memberId = loginService.join(objParam);
			session.setAttribute("memberId", memberId);
			
		}
  
		resultMap.put("url", "/");
		return resultMap;

    }
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/api/saveMember", method = RequestMethod.POST)
	public HashMap<String, String> saveMember(@RequestBody HashMap<String, String> map, HttpServletRequest request){
		
		HttpSession session = request.getSession();

		HashMap<String, String> resultMap = new HashMap<String, String>();
		HashMap<String,String> obj = new HashMap<String, String>();
		//resultMap.put("test", "testValue");
		  
		  
		// session ����
		String memberId = (String)session.getAttribute("memberId");
		String type = (String)session.getAttribute("type");
		  
		// form ����
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
