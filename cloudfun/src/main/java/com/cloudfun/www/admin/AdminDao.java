package com.cloudfun.www.admin;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AdminDao {

	@Autowired
	private SqlSessionTemplate sql;

	/*
	 * public List<TbRcept> rceptList(String[] arr){
		return sql.selectList("inspection.selectRceptList", arr);
	}
	 * */
}
