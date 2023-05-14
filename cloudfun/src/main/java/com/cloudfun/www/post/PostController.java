package com.cloudfun.www.post;

import java.text.DateFormat;
import java.util.ArrayList;
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
		
		
		
		String email = (String)session.getAttribute("email");
    	String name = (String)session.getAttribute("name");
    	String memberId = (String)session.getAttribute("memberId");
    	String type = (String)session.getAttribute("type");
    	

    	
    	model.addAttribute("email", email);
    	model.addAttribute("name", name);
    	model.addAttribute("type", type);
    	
    	
		objParam.put("postId", postId);
		objParam.put("groupId", "002");  // 001 메인,썸네일  2.첨부파일.
		
		Map<String, String> result = postService.selectPost(objParam);
		List<Map<String, String>> fileList = postService.selectPostFileList(objParam);
		
		
		objParam.put("groupId", "003");  // 001 메인,썸네일  2.첨부파일.
		List<Map<String, String>> fileList3 = postService.selectPostFileList(objParam);
    	//
		model.addAttribute("result", result);
		model.addAttribute("fileList", fileList);
		model.addAttribute("fileList3", fileList3);

		
		objParam.put("offset", "0");
    	objParam.put("limit", "5");
    	List<Map<String, String>> resentList = postService.selectPostList(objParam);
    	model.addAttribute("resentList", resentList);
		
    	
    	// member sponsorShip
    	objParam.put("memberId",memberId);
    	Map<String, String> resultSponAmt = postService.selectMemberSponAmt(objParam);
    	model.addAttribute("resultSponAmt", resultSponAmt);
    	
    	// 후원 순위 목록
    	List<Map<String, String>> resultRankSpon = postService.selectRankSponAmtList(objParam);
    	model.addAttribute("resultRankSpon", resultRankSpon);
    	
    	
    	// 후원코멘트 목록
    	List<Map<String, String>> resultRankComments = postService.selectRankCommentsList(objParam);
    	model.addAttribute("resultRankComments", resultRankComments);
    		
    
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
    	
    	String searchParam = request.getParameter("searchParam");
    	objParam.put("searchParam", searchParam);
    	
    	
    	int limit = 10 ;
    	
    	
    	String pageNo = request.getParameter("pageNo");
    	
    	if(pageNo == null ) {
    		pageNo = "1";
    	}
    	
    	
    	objParam.put("limit", limit+"");
    	
    	int intPageNo = Integer.parseInt(pageNo);
    	objParam.put("offset", (intPageNo * limit-limit)+"" );
    	
    	

    	objParam.put("pageNo", pageNo);
		
    	List<Map<String, String>> resultList = postService.selectPostList(objParam);
    	
    	
    	Map<String, String> resultPaging = postService.selectPostPaging(objParam);
    	
    	String strNowPage = String.valueOf(resultPaging.get("NOW_PAGE"));
    	int nowPage = Integer.parseInt(strNowPage);
    	String strLastPage = String.valueOf(resultPaging.get("LAST_PAGE"));
    	int lastPage = Integer.parseInt(strLastPage);
    	
//    	int nowPage  = Integer.valueOf(resultPaging.get("NOW_PAGE").toString());
//    	int LastPage = Integer.valueOf(resultPaging.get("LAST_PAGE").toString());
    	int tmpLastPage = nowPage+4; // 마지막 페이지  1~5 인우 5임.6~10인경우 10 
    	
    	String arrowYN = "Y";
    	
    	if(lastPage <= tmpLastPage) {
    		tmpLastPage = lastPage ;
    		arrowYN = "N";
    	}
    	
    	List<Integer> pageList = new ArrayList<>();
    	for(int i = nowPage ; i <=tmpLastPage ; i++ ) {
    		pageList.add(i);
    	}
    	
    	    	
    	
    	// 다음페이지 여부
    	model.addAttribute("arrowYN", arrowYN);
    	// 현재페이지
    	model.addAttribute("pageNo", pageNo);
    	// 페이지리스트 
    	model.addAttribute("pageList", pageList);
    	
    	model.addAttribute("arrowNextPage", tmpLastPage+1); // 10인경우 다음페이지는 11이므로 +1
    	model.addAttribute("arrowBeforePage", tmpLastPage-5);  // 10  인경우 5여야하므로 -5
    	
    	
    	
    	objParam.put("offset", "0");
    	objParam.put("limit", "5");
    	List<Map<String, String>> resentList = postService.selectPostList(objParam);
    	
    	
    	model.addAttribute("resultList", resultList);
    	model.addAttribute("resentList", resentList);
    	model.addAttribute("resultPaging", resultPaging);
    	
    	

		//return "home";
		return "post/postList";
	}
	
	
	
	// 나의 창작물 목록
	//myPostList
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/post/myPostList", method = RequestMethod.GET)
	public String myPostList(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		String email = (String)session.getAttribute("email");
    	String name = (String)session.getAttribute("name");
    	String memberId = (String)session.getAttribute("memberId");
    	String type = (String)session.getAttribute("type");
    	
    	
    	if(memberId == null ) {
    		//return "home";
    		return "mainPage";
    	}
    	
    	
    	
    	Map<String, String> objParam = new HashMap<String,String>();
    	
    	objParam.put("email", email);
    	objParam.put("name", name);
    	objParam.put("memberId", memberId);
    	objParam.put("type", type);
    	
    	objParam.put("myPost", "Y");

    	
    	
    	int limit = 10 ;
    	
    	
    	String pageNo = request.getParameter("pageNo");
    	
    	if(pageNo == null ) {
    		pageNo = "1";
    	}
    	
    	
    	objParam.put("limit", limit+"");
    	
    	int intPageNo = Integer.parseInt(pageNo);
    	objParam.put("offset", (intPageNo * limit-limit)+"" );
    	
    	objParam.put("pageNo", pageNo);
		
    	List<Map<String, String>> resultList = postService.selectPostList(objParam);
    	
    	
    	Map<String, String> resultPaging = postService.selectPostPaging(objParam);
    	
    	String strNowPage = String.valueOf(resultPaging.get("NOW_PAGE"));
    	int nowPage = Integer.parseInt(strNowPage);
    	String strLastPage = String.valueOf(resultPaging.get("LAST_PAGE"));
    	int lastPage = Integer.parseInt(strLastPage);
    	
//    	int nowPage  = Integer.valueOf(resultPaging.get("NOW_PAGE").toString());
//    	int LastPage = Integer.valueOf(resultPaging.get("LAST_PAGE").toString());
    	int tmpLastPage = nowPage+4; // 마지막 페이지  1~5 인우 5임.6~10인경우 10 
    	
    	String arrowYN = "Y";
    	
    	if(lastPage <= tmpLastPage) {
    		tmpLastPage = lastPage ;
    		arrowYN = "N";
    	}
    	
    	List<Integer> pageList = new ArrayList<>();
    	for(int i = nowPage ; i <=tmpLastPage ; i++ ) {
    		pageList.add(i);
    	}
    	
    	    	
    	
    	// 다음페이지 여부
    	model.addAttribute("arrowYN", arrowYN);
    	// 현재페이지
    	model.addAttribute("pageNo", pageNo);
    	// 페이지리스트 
    	model.addAttribute("pageList", pageList);
    	
    	model.addAttribute("arrowNextPage", tmpLastPage+1); // 10인경우 다음페이지는 11이므로 +1
    	model.addAttribute("arrowBeforePage", tmpLastPage-5);  // 10  인경우 5여야하므로 -5
    	
    	
    	/*
    	objParam.put("offset", "0");
    	objParam.put("limit", "5");
    	List<Map<String, String>> resentList = postService.selectPostList(objParam);
    	*/
    	
    	model.addAttribute("resultList", resultList);
    	//model.addAttribute("resentList", resentList);
    	model.addAttribute("resultPaging", resultPaging);
    	
    	

		//return "home";
		return "post/myPostList";
	}
	
	
	@RequestMapping(value = "/post/postEdit", method = RequestMethod.GET)
	public String postEdit(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		String email = (String)session.getAttribute("email");
    	String name = (String)session.getAttribute("name");
    	String memberId = (String)session.getAttribute("memberId");
    	String type = (String)session.getAttribute("type");
    	
    	String postId = request.getParameter("postId");
    	
    	if(memberId == null ) {
    		//return "home";
    		return "mainPage";
    	}
    	
    	Map<String, String> objParam = new HashMap<String,String>();
    	
    	
    	objParam.put("type", type);
    	objParam.put("postId", postId);
    	objParam.put("memberId", memberId);
    	
    	Map<String, String> result = postService.selectPost(objParam);
    	String postType = result.get("DOMAIN_TYPE");
    	
    	// postType 
    	/*
    	  * 글 : text
	      * 그림 : picture
	      * 만화 : comic
	      * 음악 : music
          * 3D Model : model
    	 * */
    	String url = request.getContextPath();
    	switch(postType) {
    	  case "text":
    		  url = url+"/post/editText?postId=" + postId;
    	    break;
    	  case "picture":
    		  url = url+"/post/editPicture?postId=" + postId;
    	    break;
    	  case "comic":
    		  url = url+"/post/editComic?postId=" + postId;
      	    break;
    	  case "music":
    		  url = url+"/post/editMusic?postId=" + postId;
      	    break;
    	  case "model":
    		  url = url+"/post/edit3D?postId=" + postId;
      	    break;
    	  default:
    	}
    	
//    	
//    	model.addAttribute("arrowNextPage", tmpLastPage+1); // 10인경우 다음페이지는 11이므로 +1
//    	model.addAttribute("arrowBeforePage", tmpLastPage-5);  // 10  인경우 5여야하므로 -5

		//return "home";
		return "redirect: "+url;
	}
	
}
