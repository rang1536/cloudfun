package com.cloudfun.www.post;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudfun.www.login.service.LoginService;
import com.cloudfun.www.post.service.PostService;
import com.cloudfun.www.post.vo.Post;
import com.cloudfun.www.util.UtilFile;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PostRestController {
	private static final Logger logger = LoggerFactory.getLogger(PostRestController.class);


	@Value("#{globals['upload.file.path']}") 
    private String filePath;

	 
	@Autowired
	private PostService postService;
	
	@Autowired
	private UtilFile utilFile;
	
	
	
	/**
	 * example: component.jsp에서 사용.
	 */
	@RequestMapping(value="/api/post", method = RequestMethod.POST)
	public HashMap<String, String> joinr(@RequestPart("uploadFile") MultipartFile[] uploadFile
			, @RequestParam("test") String customField, HttpServletRequest request){
		
		HttpSession session = request.getSession();

		HashMap<String, String> resultMap = new HashMap<String, String>();
		
		for(MultipartFile multipartFile : uploadFile) {
			resultMap = utilFile.uploadFile(multipartFile, filePath);
		}
		
		
				
		return resultMap;

    }
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value="/api/post/save", method = RequestMethod.POST)
	public HashMap<String, String> Save(@RequestPart("uploadFile") MultipartFile[] uploadFile
			, @RequestPart("mainImg") MultipartFile[] mainImg
			, @RequestPart(value="desImg", required = false) MultipartFile[] desImg
			, @RequestParam("jsonStr") String jsonStr
			,  HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{
		
		
		HttpSession session = request.getSession();
		// json(form) to map
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,String> obj = objectMapper.readValue(jsonStr, Map.class);
		
		// insert, update memberId 사용
		obj.put("memberId",(String)session.getAttribute("memberId"));
		obj.put("domainType",(String)session.getAttribute("type"));
		
		// 신규 post번호 생성
		String postId = postService.selectPostId(obj);
		
		obj.put("postId",postId);
		
		
		// 파일 업로드 선행후 파일 ID 저장.
		HashMap<String, String> resultMap = new HashMap<String, String>();
		
		
		
		HashMap<String, String> param = new HashMap<String, String>();

		/*file group id 
		 * 001 : 메인화면 썸네일 이미지.
		 * 002 : 컨텐츠파일
		 * 003 : 설명이미지 (3d에서 사용)
		 * */
		obj.put("groupId","003");
		for(MultipartFile multipartFile : desImg) {
			//resultMap = utilFile.uploadFile(multipartFile, filePath);
			utilFile.uploadFile(multipartFile,obj) ;
		}
		
		obj.put("groupId","002");
		for(MultipartFile multipartFile : uploadFile) {
			//resultMap = utilFile.uploadFile(multipartFile, filePath);
			utilFile.uploadFile(multipartFile,obj) ;
		}
		
		obj.put("groupId","001");
		for(MultipartFile multipartFile : mainImg) {
			//resultMap = utilFile.uploadFile(multipartFile, filePath);
			utilFile.uploadFile(multipartFile,obj) ;
		}
		
		obj.put("postId",postId);
		postService.insertPost(obj);
		
		
		//result set
		resultMap.put("postId",postId);
		
		return resultMap;

    }
	
	
	
	@RequestMapping("/display")
	public ResponseEntity<Resource> display(@RequestParam("filename") String filename) {
		String path = filePath;
		String folder = "";
		if(filename.equals("1")) {
			filename="20230412070039ec1ae3e3b1a04fbb8e5afc6d718dfa17.jpg";
		}
		/*filename = "s_20230411212455963dd6d9c19f488fa325be51a0c8c949.png";*/
		Resource resource = new FileSystemResource(path + folder + filename);
		if(!resource.exists()) 
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		HttpHeaders header = new HttpHeaders();
		Path filePath = null;
		try{
			filePath = Paths.get(path + folder + filename);
			header.add("Content-type", Files.probeContentType(filePath));
		}catch(IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
	
	
	@RequestMapping("/download")
	public ResponseEntity<Resource> download(@RequestParam("filename") String filename) {
		String path = filePath;
		String folder = "";
		if(filename.equals("1")) {
			filename="20230412070039ec1ae3e3b1a04fbb8e5afc6d718dfa17.jpg";
		}
		/*filename = "s_20230411212455963dd6d9c19f488fa325be51a0c8c949.png";*/
		Resource resource = new FileSystemResource(path + folder + filename);
		
		String resourceName = resource.getFilename();
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		
		try {
			headers.add("Content-Disposition"
					, "attachment; filename="+ new String(resourceName.getBytes("UTF-8"),"ISO-8859-1"));
			
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
		
	}
	
	
	
	/*@Value("${spring.servlet.multipart.location}")
	String filePath;

	@GetMapping("/download")
	public ResponseEntity<Resource> download(@ModelAttribute FileDto dto) throws IOException {

		Path path = Paths.get(filePath + "/" + dto.getFileName());
		String contentType = Files.probeContentType(path);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(
		ContentDisposition.builder("attachment")
			.filename(dto.getFileName(), StandardCharsets.UTF_8)
			.build());
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);

		Resource resource = new InputStreamResource(Files.newInputStream(path));

		return new ResponseEntity<>(resource, headers, HttpStatus.OK);

	}*/
	
	
	// 후원 금액 지불
	// /api/sponsorship
	/**
	 * 
	 */
	@RequestMapping(value="/api/sponsorship", method = RequestMethod.POST)
	public HashMap<String, String> sponsorship(@RequestBody HashMap<String, String> map, HttpServletRequest request){
		
		HttpSession session = request.getSession();

		HashMap<String, String> resultMap = new HashMap<String, String>();
		  
		  
		// session 정보
		String sessionEmail = (String)session.getAttribute("email");
		String sessionName = (String)session.getAttribute("name");
		String sessionType = (String)session.getAttribute("type");
		String memberId = (String)session.getAttribute("memberId");
		  
		
		// form 정보
		String postId = map.get("postId");
		String amt = map.get("amt");
		
		  
		// 1. 후원테이블에 저장
		Map<String, String> objParam = new HashMap<String,String>();
		objParam.put("amt", amt);
		objParam.put("postId", postId);
		objParam.put("memberId", memberId);
		
		postService.setSponsership(objParam);
		
		Map<String, String> resultSponAmt = postService.selectMemberSponAmt(objParam);
	
		resultMap.put("SUM_AMT", String.format("%.2f", resultSponAmt.get("SUM_AMT")));
  
		return resultMap;
	}
	
	
	
	// 후원 금액 지불
		// /api/sponsorship
		/**
		 * 
		 */
		@RequestMapping(value="/api/sponComments", method = RequestMethod.POST)
		public HashMap<String, String> sponComments(@RequestBody HashMap<String, String> map, HttpServletRequest request){
			
			HttpSession session = request.getSession();

			HashMap<String, String> resultMap = new HashMap<String, String>();
			  
			  
			// session 정보
			String sessionEmail = (String)session.getAttribute("email");
			String sessionName = (String)session.getAttribute("name");
			String sessionType = (String)session.getAttribute("type");
			String memberId = (String)session.getAttribute("memberId");
			  
			
			// form 정보
			String postId = map.get("postId");
			String sponMessage = map.get("sponMessage");
			
			  
			// 1. 후원테이블에 저장
			Map<String, String> objParam = new HashMap<String,String>();
			objParam.put("sponMessage", sponMessage);
			objParam.put("postId", postId);
			objParam.put("memberId", memberId);
			
			postService.insertSponComments(objParam);
			
			/*Map<String, String> resultSponAmt = postService.selectMemberSponAmt(objParam);*/
		
			/*resultMap.put("SUM_AMT", String.format("%.2f", resultSponAmt.get("SUM_AMT")));*/
	  
			return resultMap;
		}
		
		
		// 관리자에게 신고
		@RequestMapping(value="/api/alertMessage", method = RequestMethod.POST)
		public HashMap<String, String> alertMessage(@RequestBody HashMap<String, String> map, HttpServletRequest request){
			
			HttpSession session = request.getSession();

			HashMap<String, String> resultMap = new HashMap<String, String>();
			  
			  
			// session 정보
			String sessionEmail = (String)session.getAttribute("email");
			String sessionName = (String)session.getAttribute("name");
			String sessionType = (String)session.getAttribute("type");
			String memberId = (String)session.getAttribute("memberId");
			  
			
			// form 정보
			String postId = map.get("postId");
			String alertMessage = map.get("alertMessage");
			
			  
			// 1. 후원테이블에 저장
			Map<String, String> objParam = new HashMap<String,String>();
			objParam.put("alertMessage", alertMessage);
			objParam.put("postId", postId);
			objParam.put("memberId", memberId);
			
			postService.insertAlertMessage(objParam);
			
			/*Map<String, String> resultSponAmt = postService.selectMemberSponAmt(objParam);*/
		
			/*resultMap.put("SUM_AMT", String.format("%.2f", resultSponAmt.get("SUM_AMT")));*/
	  
			return resultMap;
		}
		
		
		
		// 편집시 이전데이터 불러오기.
		@RequestMapping(value="/api/post/getPostData", method = RequestMethod.POST)
		public HashMap<String, Object> getPostData(@RequestBody HashMap<String, String> map, HttpServletRequest request){
			
			HttpSession session = request.getSession();

			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			  
			  
			Map<String, String> objParam = new HashMap<String,String>();
			
			//session.setAttribute("type", "text");
			
			String email = (String)session.getAttribute("email");
	    	String name = (String)session.getAttribute("name");
	    	String memberId = (String)session.getAttribute("memberId");
	    	String postId = map.get("postId");
	    	
	    	resultMap.put("email", email);
	    	resultMap.put("name", name);
	    	
			objParam.put("postId", postId);
			objParam.put("groupId", "002");  // 001 메인,썸네일  2.첨부파일.
			
			Map<String, String> result = postService.selectPost(objParam);
			List<Map<String, String>> fileList = postService.selectPostFileList(objParam);
			
			
			objParam.put("groupId", "003");  // 001 메인,썸네일  2.첨부파일.
			List<Map<String, String>> fileList3 = postService.selectPostFileList(objParam);
	    	//
			resultMap.put("result", result);
			resultMap.put("fileList", fileList);
			resultMap.put("fileList3", fileList3);

			
			
	    	
	    	// member sponsorShip
	    	objParam.put("memberId",memberId);
	    	Map<String, String> resultSponAmt = postService.selectMemberSponAmt(objParam);
	    	resultMap.put("resultSponAmt", resultSponAmt);
	    	
	    	// 후원 순위 목록
	    	List<Map<String, String>> resultRankSpon = postService.selectRankSponAmtList(objParam);
	    	resultMap.put("resultRankSpon", resultRankSpon);
	    	
	    	
	    	// 후원코멘트 목록
	    	List<Map<String, String>> resultRankComments = postService.selectRankCommentsList(objParam);
	    	resultMap.put("resultRankComments", resultRankComments);
			
			return resultMap;
		}
		
		
		
		
		
		
}
