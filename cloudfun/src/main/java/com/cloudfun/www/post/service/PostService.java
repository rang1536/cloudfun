package com.cloudfun.www.post.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudfun.www.login.dao.LoginDao;
import com.cloudfun.www.post.dao.PostDao;

@Service
public class PostService {
	
	
	@Autowired
	private PostDao postDao;
	/*
	public String isLogin(Map<String, String> obj) {
		
		List<Map<String, String>> arrUser = loginDao.rceptList(obj);
		String result = null ;
		
		//int result =Integer.parseInt(String.valueOf(a.get(0).get("CNT")));
		if(arrUser.size()>0) {
			result =(String)arrUser.get(0).get("MEMBER_ID");
		}
		
		return result;
		
	}
	
	// ȸ������
	public String join(Map<String, String> obj) {
		
		
		//	 	3. ȸ���� �ƴѰ�� ȸ������..
		//	 	3-1 ȸ�����̺� merge
		loginDao.mergeMember(obj);

		//	 	3-2 ȸ�����̺� ��ȸ
		// ȸ��id ä�� 
		String memberId = isLogin(obj);
		obj.put("memberId", memberId);
		
		
		//    	3-2 ȸ�� �� ���̺� insert
		loginDao.insertMemberDetail(obj);
		
		
		return memberId;
		
	}*/
	
	

}
