package com.cloudfun.www.login.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudfun.www.login.dao.LoginDao;

@Service
public class LoginService {
	
	
	@Autowired
	private LoginDao loginDao;
	
	public String isLogin(Map<String, String> obj) {
		
		List<Map<String, String>> arrUser = loginDao.rceptList(obj);
		String result = null ;
		
		//int result =Integer.parseInt(String.valueOf(a.get(0).get("CNT")));
		if(arrUser.size()>0) {
			result =(String)arrUser.get(0).get("MEMBER_ID");
		}
		
		return result;
		
	}
	
	// 회원가입
	public String join(Map<String, String> obj) {
		
		
		//	 	3. 회원이 아닌경우 회원가입..
		//	 	3-1 회원테이블 merge
		loginDao.mergeMember(obj);

		//	 	3-2 회원테이블 조회
		// 회원id 채번 
		String memberId = isLogin(obj);
		obj.put("memberId", memberId);
		
		
		//    	3-2 회원 상세 테이블 insert
		loginDao.insertMemberDetail(obj);
		
		return memberId;
		
	}

	public Map<String, String> selectMember(Map<String, String> obj) {
		// TODO Auto-generated method stub
		return loginDao.selectMember(obj);
	}

	public void saveMemberDetail(HashMap<String, String> obj) {
//    	3-2 회원 상세 테이블 insert
		loginDao.saveMemberDetail(obj);
		
	}

	public List<Map<String, String>> selectNationCd(HashMap<String, String> obj) {
		// TODO Auto-generated method stub
		return loginDao.selectNationCd(obj);
	}
	
	

}
