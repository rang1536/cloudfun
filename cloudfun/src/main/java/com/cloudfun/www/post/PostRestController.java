package com.cloudfun.www.post;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	 * example: component.jsp���� ���.
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
			, @RequestParam("jsonStr") String jsonStr
			,  HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{
		
		
		HttpSession session = request.getSession();
		// json(form) to map
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,String> obj = objectMapper.readValue(jsonStr, Map.class);
		
		// insert, update memberId ���
		obj.put("memberId",(String)session.getAttribute("memberId"));
		obj.put("domainType",(String)session.getAttribute("type"));
		
		// �ű� post��ȣ ����
		String postId = postService.selectPostId(obj);
		
		obj.put("postId",postId);
		
		
		// ���� ���ε� ������ ���� ID ����.
		HashMap<String, String> resultMap = new HashMap<String, String>();
		
		
		
		HashMap<String, String> param = new HashMap<String, String>();

		for(MultipartFile multipartFile : uploadFile) {
			//resultMap = utilFile.uploadFile(multipartFile, filePath);
			resultMap = utilFile.uploadFile(multipartFile,obj) ;
		}
		
		
		
		

		
		obj.put("postId",postId);
		
		
		
		postService.insertPost(obj);
		

				
		return resultMap;

    }
	
	
	
	
	
	
	
	
}