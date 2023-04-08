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
	 
	 // 사용자 정보 merge : 사이트별로 타입을 업데이트 하고 최초가입시 insert한다.
	 // PARAM  : type, email, name, 
	 public int mergeMember(Map<String,String> obj) {
		 
		 int result =  sql.update("login.mergeMember", obj);
		 return result;
		 
	 }
	 */

	
	 // 포스트 신규 아이디를 얻는다.
	 public String selectPostId(Map<String,String> obj) {
		 
		 String postId=  sql.selectOne("post.selectPostId");
 		 return postId;
		 
	 }
	 
	 // 상세정보(비밀번호 + 프로필)은 사이트마다 개별로 관리한다.
	 // PARAM : memberId, type, password, birth
	 public int insertPost(Map<String,String> obj) {
		 
		 int result =  sql.insert("post.insertPost", obj);
		 return result;
		 
	 }
	 
	
	 

	 
	 
	 
	 
}
