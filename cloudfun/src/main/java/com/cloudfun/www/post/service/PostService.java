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
	
	public void insertPost(Map<String, String> obj) {
		
		// post 등록
 		postDao.insertPost(obj);
	}
	
	public String selectPostId(Map<String, String> obj) {
		
		// post 등록
		return postDao.selectPostId(obj);
	}
	
	
	public List<Map<String, String>> selectPostList(Map<String, String> obj) {
		
		// post 등록
		return postDao.selectPostList(obj);
	}
	
	
	public Map<String, String> selectPost(Map<String, String> obj) {
		
		// post 등록
		return postDao.selectPost(obj);
	}

	public List<Map<String, String>> selectPostFileList(Map<String, String> obj) {
		// post 등록
		return postDao.selectPostFileList(obj);
	}
	
	
	
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
		
	}*/
	
	

}
