package com.cloudfun.www.login.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDao {
	@Autowired
	private SqlSessionTemplate sql;

	 public List<Map<String,Object>> rceptList(Map<String,Object> obj){
		 
		 List<Map<String, Object>> result =  sql.selectList("login.userCheck", obj);
		 return result;
	}
	 
}
