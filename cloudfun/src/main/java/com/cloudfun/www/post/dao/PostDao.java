package com.cloudfun.www.post.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostDao {
	@Autowired
	private SqlSessionTemplate sql;

	/*// param : email
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
	 */

	
	 // ����Ʈ �ű� ���̵� ��´�.
	 public String selectPostId(Map<String,String> obj) {
		 
		 String postId=  sql.selectOne("post.selectPostId");
 		 return postId;
		 
	 }
	 
	 // ������(��й�ȣ + ������)�� ����Ʈ���� ������ �����Ѵ�.
	 // PARAM : memberId, type, password, birth
	 public int insertPost(Map<String,String> obj) {
		 
		 int result =  sql.insert("post.insertPost", obj);
		 return result;
		 
	 }
	 
	
	 

	 
	 
	 
	 
}