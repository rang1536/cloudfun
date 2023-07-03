package com.cloudfun.www;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


	@Value("#{globals['upload.file.path']}")
    private String filePath;


	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		// todo
        HttpSession session = request.getSession();

		if(session.getAttribute("localCountry") == null ) {
			session.setAttribute("localCountry", request.getLocale().getCountry()); // KR
		}

		//return "home";
		return "mainPage";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/component", method = RequestMethod.GET)
	public String componentTemplate(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		//return "home";
		return "component";
	}


	///terms
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/terms", method = RequestMethod.GET)
	public String terms(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		//return "home";
		return "terms";
	}

	// 
    @RequestMapping("/fileDownload/{file}")
    public void fileDownload(@PathVariable String file,
                             HttpServletResponse response) throws IOException {

    	file = "60c1a4205d2545b6862a1649c2785c7c.docx";
        File f = new File(filePath, file);
        // file 다운로드 테스트
        response.setContentType("application/download");
        response.setContentLength((int)f.length());
        file="testNM.docx";
        response.setHeader("Content-disposition", "attachment;filename=\"" + file + "\"");
        // response
        OutputStream os = response.getOutputStream();
       
        FileInputStream fis = new FileInputStream(f);
        FileCopyUtils.copy(fis, os);
        fis.close();
        os.close();
    }

    // 지역변경 다국어처리
    @RequestMapping("/local/{lang}")
    public String localLang(@PathVariable String lang
    		, HttpServletRequest request) throws IOException {
    	// todo
        HttpSession session = request.getSession();
		session.setAttribute("localCountry", lang);

		String referer = request.getHeader("Referer"); // 
		return "redirect:"+ referer;
    }

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
	public String papalTest() {

		return "payapalTest";
	}
    
    // 타입변경 
    @RequestMapping("/setType/{lang}") 
    public String setType(@PathVariable String lang
    		, HttpServletRequest request) throws IOException {
    	// todo
        HttpSession session = request.getSession();
		session.setAttribute("type", lang);
    	
		String referer = request.getHeader("Referer"); //
		return "redirect:"+ "/";
    }
    
}
