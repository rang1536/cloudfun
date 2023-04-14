package com.cloudfun.www.com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileDao {
	@Autowired
	private SqlSessionTemplate sql;
 
	 // �������� ���ε�.
	 public int insertFile(Map<String,String> obj) {
		 int result =  sql.insert("com.insertFile", obj);
		 return result;
	 }
	 
	 // �������� ���ε�.
	 public int updateThumbnail(Map<String,String> obj) {
		 int result =  sql.update("com.updateThumbnail", obj);
		 return result;
	 }

	public int updateTxtPreview(HashMap<String, String> obj) {
		 int result =  sql.update("com.updateTxtPreview", obj);
		 return result;
		
	}
	 
	 
	 
	 
}
