package com.cloudfun.www.admin;

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
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminRestController {
	private static final Logger logger = LoggerFactory.getLogger(AdminRestController.class);

	
	@Autowired
	private AdminService adminService;
	
	

	@RequestMapping(value="/api/admin/getMemberList", method = RequestMethod.POST)
	public HashMap<String, Object> getMemberList(@RequestBody HashMap<String, String> map, HttpServletRequest request){
		
		HttpSession session = request.getSession();

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		  
		  
		// session 정보
		String sessionEmail = (String)session.getAttribute("email");
		String sessionName = (String)session.getAttribute("name");
		String sessionType = (String)session.getAttribute("type");
		String memberId = (String)session.getAttribute("memberId");
		  
		
		// form 정보
//		String postId = map.get("postId");
//		String amt = map.get("amt");

		Map<String, String> objParam = new HashMap<String,String>();
		List<Map<String, String>> memberList =adminService.selectMemberList(objParam);
		resultMap.put("memberList", memberList);
		
		return resultMap;
	}
	
	
	@RequestMapping(value="/api/admin/setMemberInfo", method = RequestMethod.POST)
	public HashMap<String, Object> setMemberInfo(@RequestBody HashMap<String, String> map, HttpServletRequest request){
		
		HttpSession session = request.getSession();

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		  
		  
		// session 정보
		String sessionEmail = (String)session.getAttribute("email");
		String sessionName = (String)session.getAttribute("name");
		String sessionType = (String)session.getAttribute("type");
		
		
		
		String memberId = map.get("memberId");
		String useYn = map.get("useYn");
		String adminYn = map.get("adminYn");
		
		
		// form 정보
//		String postId = map.get("postId");
//		String amt = map.get("amt");

		Map<String, String> objParam = new HashMap<String,String>();
		
		objParam.put("memberId", memberId);
		objParam.put("useYn", useYn);
		objParam.put("adminYn", adminYn);
		
		adminService.updateMemberInfo(objParam);
		
		
		return resultMap;
	}
	
	
	
	@RequestMapping(value="/api/admin/getPostList", method = RequestMethod.POST)
	public HashMap<String, Object> getPostList(@RequestBody HashMap<String, String> map, HttpServletRequest request){
		
		HttpSession session = request.getSession();

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		  
		  
		// session 정보
		String sessionEmail = (String)session.getAttribute("email");
		String sessionName = (String)session.getAttribute("name");
		String sessionType = (String)session.getAttribute("type");
		String memberId = (String)session.getAttribute("memberId");
		  
		String adminType = (String)map.get("adminType");

		// form 정보
//		String postId = map.get("postId");
//		String amt = map.get("amt");

		Map<String, String> objParam = new HashMap<String,String>();
		objParam.put("testValue", adminType);
		List<Map<String, String>> memberList =adminService.selectPostList(objParam);
		resultMap.put("memberList", memberList);
		
		return resultMap;
	}
	
	
	@RequestMapping(value="/api/admin/setPostInfo", method = RequestMethod.POST)
	public HashMap<String, Object> setPostInfo(@RequestBody HashMap<String, String> map, HttpServletRequest request){
		
		HttpSession session = request.getSession();

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		  
		  
		// session 정보
		String sessionEmail = (String)session.getAttribute("email");
		String sessionName = (String)session.getAttribute("name");
		String sessionType = (String)session.getAttribute("type");
		
		
		
		String postId = map.get("postId");
		String useYn = map.get("useYn");
		
		
		// form 정보
//		String postId = map.get("postId");
//		String amt = map.get("amt");

		Map<String, String> objParam = new HashMap<String,String>();
		
		objParam.put("postId", postId);
		objParam.put("useYn", useYn);
		
		adminService.updatePostInfo(objParam);
		
		
		return resultMap;
	}
	
	
	
	@RequestMapping(value="/api/admin/getAlertList", method = RequestMethod.POST)
	public HashMap<String, Object> getAlertList(@RequestBody HashMap<String, String> map, HttpServletRequest request){
		
		HttpSession session = request.getSession();

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		  
		  
		// session 정보
		String sessionEmail = (String)session.getAttribute("email");
		String sessionName = (String)session.getAttribute("name");
		String sessionType = (String)session.getAttribute("type");
		String memberId = (String)session.getAttribute("memberId");
		  
		String adminType = (String)map.get("adminType");
		String useYn = (String)map.get("useYn");

		// form 정보
//		String postId = map.get("postId");
//		String amt = map.get("amt");

		Map<String, String> objParam = new HashMap<String,String>();
		objParam.put("adminType", adminType);
		objParam.put("useYn", useYn);
		List<Map<String, String>> memberList =adminService.selectAlertList(objParam);
		resultMap.put("memberList", memberList);
		
		return resultMap;
	}
	
	
	@RequestMapping(value="/api/admin/setAlertInfo", method = RequestMethod.POST)
	public HashMap<String, Object> setAlertInfo(@RequestBody HashMap<String, String> map, HttpServletRequest request){
		
		HttpSession session = request.getSession();

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		  
		  
		// session 정보
		String sessionEmail = (String)session.getAttribute("email");
		String sessionName = (String)session.getAttribute("name");
		String sessionType = (String)session.getAttribute("type");
		
		
		
		String reportId = map.get("reportId");
		String useYn = map.get("useYn");
		
		
		// form 정보
//		String postId = map.get("postId");
//		String amt = map.get("amt");

		Map<String, String> objParam = new HashMap<String,String>();
		
		objParam.put("reportId", reportId);
		objParam.put("useYn", useYn);
		
		adminService.updateAlertInfo(objParam);
		
		
		return resultMap;
	}
	
}
