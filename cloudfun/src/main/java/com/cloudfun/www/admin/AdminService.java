package com.cloudfun.www.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudfun.www.post.dao.PostDao;


@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	

	public Map<String, String> getAdminYn(Map<String, String> objParam) {
		// TODO Auto-generated method stub
		return adminDao.getAdminYn(objParam);
	}
	/*
	 * @Autowired
	private InspectDao inspectDao;
	 * */


	public List<Map<String, String>> selectMemberList(Map<String, String> objParam) {
		return adminDao.selectMemberList(objParam);
	}


	public void updateMemberInfo(Map<String, String> objParam) {
		adminDao.updateMemberInfo(objParam);
		
	}


	public List<Map<String, String>> selectPostList(Map<String, String> objParam) {
		return adminDao.selectPostList(objParam);
	}


	public void updatePostInfo(Map<String, String> objParam) {
		adminDao.updatePostInfo(objParam);
		
	}


	public List<Map<String, String>> selectAlertList(Map<String, String> objParam) {
		return adminDao.selectAlertList(objParam);
	}


	public void updateAlertInfo(Map<String, String> objParam) {
		adminDao.updateAlertInfo(objParam);
		
	}
}
