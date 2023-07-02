package com.cloudfun.www.admin;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AdminDao {

	@Autowired
	private SqlSessionTemplate sql;

	public Map<String, String> getAdminYn(Map<String, String> objParam) {
		Map<String, String> result =  sql.selectOne("admin.getAdminYn", objParam);
		 return result;
	}

	public List<Map<String, String>> selectMemberList(Map<String, String> objParam) {
		List<Map<String, String>> result =  sql.selectList("admin.selectMemberList", objParam);
		return result;
	}

	public void updateMemberInfo(Map<String, String> objParam) {
		 int result =  sql.update("admin.updateMemberInfo", objParam);
		
	}

	public List<Map<String, String>> selectPostList(Map<String, String> objParam) {
		List<Map<String, String>> result =  sql.selectList("admin.selectPostList2", objParam);
		return result;
	}

	public void updatePostInfo(Map<String, String> objParam) {
		 int result =  sql.update("admin.updatePostInfo", objParam);
		
	}

	public List<Map<String, String>> selectAlertList(Map<String, String> objParam) {
		List<Map<String, String>> result =  sql.selectList("admin.selectAlertList", objParam);
		return result;
	}

	public void updateAlertInfo(Map<String, String> objParam) {
		 int result =  sql.update("admin.updateAlertInfo", objParam);
		
	}

	/*
	 * public List<TbRcept> rceptList(String[] arr){
		return sql.selectList("inspection.selectRceptList", arr);
	}
	 * */
}
