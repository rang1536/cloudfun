package com.cloudfun.www.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cloudfun.www.post.service.PostService;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	/**
	 * member 관리자 페이지.
	 */
	@RequestMapping(value = "/admin/member", method = RequestMethod.GET)
	public String member(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
    	String memberId = (String)session.getAttribute("memberId");
    	
    	if(memberId == null ) {
    		//return "home";
    		return "mainPage";
    	}
    	
    	Map<String, String> objParam = new HashMap<String,String>();
    	objParam.put("memberId", memberId);

    	/// 관리자 권한 체크
    	Map<String, String> resultPaging = adminService.getAdminYn(objParam);
    	
    	String adminYn = resultPaging.get("ADMIN_YN");
    	if(adminYn!=null && !adminYn.equals("Y")) {
    		//return "home";
    		return "mainPage";
    	}


		//return "home";
		return "/admin/member";
	}
	
	
	/**
	 * member 관리자 페이지.
	 */
	@RequestMapping(value = "/admin/adminPost", method = RequestMethod.GET)
	public String adminPost(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
    	String memberId = (String)session.getAttribute("memberId");
    	
    	if(memberId == null ) {
    		//return "home";
    		return "mainPage";
    	}
    	
    	Map<String, String> objParam = new HashMap<String,String>();
    	objParam.put("memberId", memberId);

    	/// 관리자 권한 체크
    	Map<String, String> resultPaging = adminService.getAdminYn(objParam);
    	
    	String adminYn = resultPaging.get("ADMIN_YN");
    	if(adminYn!=null && !adminYn.equals("Y")) {
    		//return "home";
    		return "mainPage";
    	}


		//return "home";
		return "/admin/adminPost";
	}

	/**
	 * report신고 관리자 페이지.
	 */
	@RequestMapping(value = "/admin/adminReport", method = RequestMethod.GET)
	public String adminReport(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
    	String memberId = (String)session.getAttribute("memberId");
    	
    	if(memberId == null ) {
    		//return "home";
    		return "mainPage";
    	}
    	
    	Map<String, String> objParam = new HashMap<String,String>();
    	objParam.put("memberId", memberId);

    	/// 관리자 권한 체크
    	Map<String, String> resultPaging = adminService.getAdminYn(objParam);
    	
    	String adminYn = resultPaging.get("ADMIN_YN");
    	if(adminYn!=null && !adminYn.equals("Y")) {
    		//return "home";
    		return "mainPage";
    	}


		//return "home";
		return "/admin/alert";
	}
}
