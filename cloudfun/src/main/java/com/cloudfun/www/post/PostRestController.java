package com.cloudfun.www.post;

import java.io.File;
import java.io.IOException;
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
		 * */
		 
		
		obj.put("groupId","002");
		for(MultipartFile multipartFile : uploadFile) {
			//resultMap = utilFile.uploadFile(multipartFile, filePath);
			resultMap = utilFile.uploadFile(multipartFile,obj) ;
		}
		
		obj.put("groupId","001");
		for(MultipartFile multipartFile : mainImg) {
			//resultMap = utilFile.uploadFile(multipartFile, filePath);
			resultMap = utilFile.uploadFile(multipartFile,obj) ;
		}
		
		obj.put("postId",postId);
		postService.insertPost(obj);
		
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
	
	
}
