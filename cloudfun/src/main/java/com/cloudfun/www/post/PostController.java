package com.cloudfun.www.post;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloudfun.www.post.service.PostService;

@Controller
public class PostController {
private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	
	/*@Value("#{globals['google.client.id']}") 
	private String googleClientId;
	@Value("#{globals['google.client.pw']}") 
	private String googleClientPw;
	@Value("#{globals['google.redirect.url']}") 
	private String googleRedirectUrl;
	 */
	@Autowired
	private PostService postService;

	/**
	 * post 조회 페이지.
	 */
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String post(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		String email = (String)session.getAttribute("email");
    	String name = (String)session.getAttribute("name");
    	String memberId = (String)session.getAttribute("memberId");
    	

    	model.addAttribute("email", email);
    	model.addAttribute("name", name);

		//return "home";
		return "post";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/post/editText", method = RequestMethod.GET)
	public String editText(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("type", "text");
		
		String email = (String)session.getAttribute("email");
    	String name = (String)session.getAttribute("name");
    	String memberId = (String)session.getAttribute("memberId");
    	

    	model.addAttribute("email", email);
    	model.addAttribute("name", name);

		//return "home";
		return "post/editText";
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/post/editPicture", method = RequestMethod.GET)
	public String editPicture(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("type", "picture");
		
		String email = (String)session.getAttribute("email");
    	String name = (String)session.getAttribute("name");
    	String memberId = (String)session.getAttribute("memberId");
    	

    	model.addAttribute("email", email);
    	model.addAttribute("name", name);

		//return "home";
		return "post/editPicture";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/post/edit3D", method = RequestMethod.GET)
	public String edit3D(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("type", "model");
		
		String email = (String)session.getAttribute("email");
    	String name = (String)session.getAttribute("name");
    	String memberId = (String)session.getAttribute("memberId");
    	

    	model.addAttribute("email", email);
    	model.addAttribute("name", name);

		//return "home";
		return "post/edit3D";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/post/editMusic", method = RequestMethod.GET)
	public String editMusic(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("type", "music");
		
		String email = (String)session.getAttribute("email");
    	String name = (String)session.getAttribute("name");
    	String memberId = (String)session.getAttribute("memberId");
    	

    	model.addAttribute("email", email);
    	model.addAttribute("name", name);

		//return "home";
		return "post/editMusic";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/post/editComic", method = RequestMethod.GET)
	public String editComic(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("type", "comic");
		
		String email = (String)session.getAttribute("email");
    	String name = (String)session.getAttribute("name");
    	String memberId = (String)session.getAttribute("memberId");
    	String type = (String)session.getAttribute("type");
    	
    	
    	
    	

    	model.addAttribute("email", email);
    	model.addAttribute("name", name);

		//return "home";
		return "post/editComic";
	}
	
	
	/****************************************/
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/post/viewText", method = RequestMethod.GET)
	public String viewText(@RequestParam("postId") String postId, Model model, HttpServletRequest request) {
		
    	Map<String, String> objParam = new HashMap<String,String>();
		HttpSession session = request.getSession();
		
		session.setAttribute("type", "text");
		
		String email = (String)session.getAttribute("email");
    	String name = (String)session.getAttribute("name");
    	String memberId = (String)session.getAttribute("memberId");
    	

    	model.addAttribute("email", email);
    	model.addAttribute("name", name);
    	
		objParam.put("postId", postId);
		objParam.put("groupId", "002");  // 001 메인,썸네일  2.첨부파일.
		
		Map<String, String> result = postService.selectPost(objParam);
		List<Map<String, String>> fileList = postService.selectPostFileList(objParam);
		
		
    	//
		model.addAttribute("result", result);
		model.addAttribute("fileList", fileList);

		//return "home";
		return "post/viewText";
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/post/viewPicture", method = RequestMethod.GET)
	public String viewPicture(@RequestParam("postId") String postId, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("type", "picture");
		
		String email = (String)session.getAttribute("email");
    	String name = (String)session.getAttribute("name");
    	String memberId = (String)session.getAttribute("memberId");
    	

    	model.addAttribute("email", email);
    	model.addAttribute("name", name);

		//return "home";
		return "post/viewPicture";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/post/view3D", method = RequestMethod.GET)
	public String view3D(@RequestParam("postId") String postId, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("type", "model");
		
		String email = (String)session.getAttribute("email");
    	String name = (String)session.getAttribute("name");
    	String memberId = (String)session.getAttribute("memberId");
    	

    	model.addAttribute("email", email);
    	model.addAttribute("name", name);

		//return "home";
		return "post/view3D";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/post/viewMusic", method = RequestMethod.GET)
	public String viewMusic(@RequestParam("postId") String postId, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("type", "music");
		
		String email = (String)session.getAttribute("email");
    	String name = (String)session.getAttribute("name");
    	String memberId = (String)session.getAttribute("memberId");
    	

    	model.addAttribute("email", email);
    	model.addAttribute("name", name);

		//return "home";
		return "post/viewMusic";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/post/viewComic", method = RequestMethod.GET)
	public String viewComic(@RequestParam("postId") String postId, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("type", "comic");
		
		String email = (String)session.getAttribute("email");
    	String name = (String)session.getAttribute("name");
    	String memberId = (String)session.getAttribute("memberId");
    	String type = (String)session.getAttribute("type");
    	
    	model.addAttribute("email", email);
    	model.addAttribute("name", name);

		//return "home";
		return "post/viewComic";
	}
	
	
	
	
	
	
	
	/******************************************/
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/post/postList", method = RequestMethod.GET)
	public String postList(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		String email = (String)session.getAttribute("email");
    	String name = (String)session.getAttribute("name");
    	String memberId = (String)session.getAttribute("memberId");
    	String type = (String)session.getAttribute("type");
    	
    	Map<String, String> objParam = new HashMap<String,String>();
    	
    	objParam.put("email", email);
    	objParam.put("name", name);
    	objParam.put("memberId", memberId);
    	objParam.put("type", type);
		
    	List<Map<String, String>> resultList = postService.selectPostList(objParam);
    	
    	model.addAttribute("resultList", resultList);

		//return "home";
		return "post/postList";
	}
	
	
}
