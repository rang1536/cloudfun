package com.cloudfun.www.login.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudfun.www.login.dao.LoginDao;

@Service
public class LoginService {
	
	
	@Autowired
	private LoginDao loginDao;
	
	public int isLogin(Map<String, Object> obj) {
		
		List<Map<String, Object>> a = loginDao.rceptList(obj);
		
		int result =Integer.parseInt(String.valueOf(a.get(0).get("cnt")));
		
		return result;
		
	}

}
