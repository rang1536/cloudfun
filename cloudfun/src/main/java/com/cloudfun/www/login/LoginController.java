package com.cloudfun.www.login;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloudfun.www.login.service.LoginService;
import com.cloudfun.www.login.vo.GoogleInfResponse;
import com.cloudfun.www.login.vo.GoogleRequest;
import com.cloudfun.www.login.vo.GoogleResponse;

@Controller
public class LoginController {
private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	
	@Value("#{globals['google.client.id']}") 
	private String googleClientId;
	@Value("#{globals['google.client.pw']}") 
	private String googleClientPw;
	@Value("#{globals['google.redirect.url']}") 
	private String googleRedirectUrl;
	 
	@Autowired
	private LoginService loginService;

	
	// ����� ���� �α����� redirect�Ǵ� controller 
	@RequestMapping(value="/api/v1/oauth2/google", method = RequestMethod.GET)
    public String loginGoogle(@RequestParam(value = "code") String authCode, HttpServletRequest request
    		,  RedirectAttributes model ){
        String returnUrl ="redirect:/";
		RestTemplate restTemplate = new RestTemplate();
		
        GoogleRequest googleOAuthRequestParam = GoogleRequest
                .builder()
                .clientId(googleClientId)
                .clientSecret(googleClientPw)
                .code(authCode)
                .redirectUri(googleRedirectUrl)
                .grantType("authorization_code").build();
        ResponseEntity<GoogleResponse> resultEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token",
                googleOAuthRequestParam, GoogleResponse.class);
        String jwtToken=resultEntity.getBody().getId_token();
        Map<String, String> map=new HashMap<String,String>();
        map.put("id_token",jwtToken);
        ResponseEntity<GoogleInfResponse> resultEntity2 = restTemplate.postForEntity("https://oauth2.googleapis.com/tokeninfo",
                map, GoogleInfResponse.class);
        String email=resultEntity2.getBody().getEmail();
        String name=resultEntity2.getBody().getName();
        
        
        // ���� �α��� ������ �ִ��� Ȯ��.
        
        // ���� �̸����� �ִ°�� �α���ó�� 
        
        Map<String, String> a = new HashMap<String,String>();
        a.put("email", email);
        a.put("name", name);
        
        // �����ο��ٶ� Ÿ�Ա���.(��/�׸�/��ȭ/����/3D Model 5��)
	     /*
	      * �� : text
	      * �׸� : picture
	      * ��ȭ : comic
	      * ���� : music
	      * 3D Model : model
	      * */ 
        a.put("type", "text");
        
        HttpSession session = request.getSession();
        
        //int loginUserCnt= loginService.isLogin(a);
        String memberId =  loginService.isLogin(a);
        if(memberId !=null) {
        	// �ٷ� �α��� ����
        	// ���������߰�.
        	session.setAttribute("email", email);
        	session.setAttribute("name", name);
        	session.setAttribute("memberId", memberId);
        		
        }else{
        	//ȸ������ ȭ������ �̵�
        	session.setAttribute("email", email);
        	session.setAttribute("name", name);
        	
            model.addFlashAttribute("email", email );
            model.addFlashAttribute("name", name );

        	returnUrl = "redirect:/join";
        	// ȸ������ �� �������Է� ȭ������ �̵�.
        }
        
        return returnUrl;
    }
	
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		String email = (String)session.getAttribute("email");
    	String name = (String)session.getAttribute("name");

    	model.addAttribute("email", email);
    	model.addAttribute("name", name);

		//return "home";
		return "member/join";
	}
	
	
	
	
}
