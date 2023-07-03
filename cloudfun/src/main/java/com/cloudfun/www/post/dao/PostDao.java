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
	 
	 // 신규 게시물을 등록한다.
	 public int insertPost(Map<String,String> obj) {
		 
		 int result =  sql.insert("post.insertPost", obj);
		 return result;
		 
	 }
	 
	 
	 
	 
	// 게시물 목록 조회
	 public List<Map<String,String>> selectPostList(Map<String,String> obj){
		 
		 List<Map<String, String>> result =  sql.selectList("post.selectPostList", obj);
		 return result;
	}
	 
	
	// 게시물 조회(1건, 화면조회시사용)
	 public Map<String,String> selectPost(Map<String,String> obj){
		 
		 Map<String, String> result =  sql.selectOne("post.selectPost", obj);
		 return result;
	}

	public List<Map<String, String>> selectPostFileList(Map<String, String> obj) {
		
		List<Map<String, String>> result =  sql.selectList("post.selectPostFileList", obj);
		 return result;
	}

	public void insertSponsership(Map<String, String> obj) {
		 int result =  sql.insert("post.insertSponsership", obj);
	}

	public Map<String, String> selectMemberSponAmt(Map<String, String> objParam) {
		 Map<String, String> result =  sql.selectOne("post.selectMemberSponAmt", objParam);
		 return result;
	}

	public Map<String, String> selectPostPaging(Map<String, String> objParam) {
		Map<String, String> result =  sql.selectOne("post.selectPostPaging", objParam);
		 return result;
	}

	public List<Map<String, String>> selectRankSponAmtList(Map<String, String> objParam) {
		List<Map<String, String>> result =  sql.selectList("post.selectRankSponAmtList", objParam);
		 return result;
	}

	public void insertSponComments(Map<String, String> objParam) {
		 int result =  sql.insert("post.insertSponComments", objParam);
		
	}

	public void insertAlertMessage(Map<String, String> objParam) {
		 int result =  sql.insert("post.insertAlertMessage", objParam);
		
	}

	public List<Map<String, String>> selectRankCommentsList(Map<String, String> objParam) {
		List<Map<String, String>> result =  sql.selectList("post.selectRankCommentsList", objParam);
		 return result;
	}

	public void updatePost(Map<String, String> obj) {
		int result =  sql.insert("post.updatePost", obj);
	}

	public void updatePostOldFile(Map<String, String> obj) {
		 int result =  sql.insert("post.updatePostOldFile", obj);
		
	}

	public Map<String, String> selectCreaterYn(Map<String, String> obj) {
		Map<String, String> result =  sql.selectOne("post.selectCreaterYn", obj);
		return result;
	}
	
	 

	 
	 
	 
	 
}
