package com.cloudfun.www.login.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDao {
	@Autowired
	private SqlSessionTemplate sql;

	// param : email
	 public List<Map<String,String>> rceptList(Map<String,String> obj){
		 
		 List<Map<String, String>> result =  sql.selectList("login.userCheck", obj);
		 return result;
	}
	 
	 // ����� ���� merge : ����Ʈ���� Ÿ���� ������Ʈ �ϰ� ���ʰ��Խ� insert�Ѵ�.
	 // PARAM  : type, email, name, 
	 public int mergeMember(Map<String,String> obj) {
		 
		 int result =  sql.update("login.mergeMember", obj);
		 return result;
		 
	 }
	 
	 // ������(��й�ȣ + ������)�� ����Ʈ���� ������ �����Ѵ�.
	 // PARAM : memberId, type, password, birth
	 public int insertMemberDetail(Map<String,String> obj) {
		 
		 int result =  sql.insert("login.insertMemberDetail", obj);
		 return result;
		 
	 }

	public Map<String, String> selectMember(Map<String, String> obj) {
		Map<String, String> result =  sql.selectOne("login.selectMember", obj);
		return result;
	}

	public void saveMemberDetail(HashMap<String, String> obj) {
		sql.update("login.saveMemberDetail", obj);
	}
	 
	 
	 
	 
}
