package com.cloudfun.www.login;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.cloudfun.www.login.dao.LoginDao;
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
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/api/v1/oauth2/google2", method = RequestMethod.GET)
	public String loginUrlGoogle(){
		
        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
                 + "&redirect_uri="+googleRedirectUrl+"&response_type=code&scope=email%20profile%20openid&access_type=offline";
        return "redirect:"+ reqUrl;
    }
	
	
	@RequestMapping(value="/api/v1/oauth2/google", method = RequestMethod.GET)
    public String loginGoogle(@RequestParam(value = "code") String authCode){
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
        Map<String, String> map=new HashMap<>();
        map.put("id_token",jwtToken);
        ResponseEntity<GoogleInfResponse> resultEntity2 = restTemplate.postForEntity("https://oauth2.googleapis.com/tokeninfo",
                map, GoogleInfResponse.class);
        String email=resultEntity2.getBody().getEmail();
        String name=resultEntity2.getBody().getName();
        
        
        // 기존 로그인 정보가 있는지 확인.
        
        // 기존 이메일이 있는경우 로그인처리 
        
        Map<String, Object> a = new HashMap<>();
        a.put("email", email);
        a.put("name", name);
        
        
        String join ;
        int loginUserCnt= loginService.isLogin(a);
        if(loginUserCnt > 0) {
        	join = "Y";
        		
        }else{
        	join = "N";
        	
        }
        
        // 기존 이메일이 없는경우 회원가입 
        
        return "redirect:/";
    }
}
