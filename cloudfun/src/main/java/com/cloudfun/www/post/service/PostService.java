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
		
		// post ���
 		postDao.insertPost(obj);
	}
	
	public String selectPostId(Map<String, String> obj) {
		
		// post ���
		return postDao.selectPostId(obj);
	}
	
	
	public List<Map<String, String>> selectPostList(Map<String, String> obj) {
		
		// post ���
		return postDao.selectPostList(obj);
	}
	
	
	public Map<String, String> selectPost(Map<String, String> obj) {
		
		// post ��ȸ
		return postDao.selectPost(obj);
	}

	public List<Map<String, String>> selectPostFileList(Map<String, String> obj) {
		
		return postDao.selectPostFileList(obj);
	}

	public void setSponsership(Map<String, String> obj) {
		// Sponsership ���
		postDao.insertSponsership(obj);
		
	}

	public Map<String, String> selectMemberSponAmt(Map<String, String> objParam) {
		// member post�� amt��ȸ
		return postDao.selectMemberSponAmt(objParam);
	}

	public Map<String, String> selectPostPaging(Map<String, String> objParam) {
		// TODO Auto-generated method stub
		return postDao.selectPostPaging(objParam);
	}

	public List<Map<String, String>> selectRankSponAmtList(Map<String, String> objParam) {

		return postDao.selectRankSponAmtList(objParam);
	}

	public void insertSponComments(Map<String, String> objParam) {
		postDao.insertSponComments(objParam);

		
	}

	public void insertAlertMessage(Map<String, String> objParam) {
		postDao.insertAlertMessage(objParam);
		
	}

	public List<Map<String, String>> selectRankCommentsList(Map<String, String> objParam) {
		return postDao.selectRankCommentsList(objParam);
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
