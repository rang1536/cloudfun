package com.cloudfun.www.post;

import java.io.File;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudfun.www.login.service.LoginService;
import com.cloudfun.www.post.service.PostService;
import com.cloudfun.www.util.UtilFile;

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
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/api/post", method = RequestMethod.POST)
	public HashMap<String, String> joinr(@RequestParam("uploadFile") MultipartFile[] uploadFile, @RequestParam("test") String customField, HttpServletRequest request){
		
		HttpSession session = request.getSession();

		HashMap<String, String> resultMap = new HashMap<String, String>();
		
		for(MultipartFile multipartFile : uploadFile) {
			resultMap = utilFile.uploadFile(multipartFile, filePath);
		}
		
				
		return resultMap;

    }
	
	
	
	
	
	
	
}
