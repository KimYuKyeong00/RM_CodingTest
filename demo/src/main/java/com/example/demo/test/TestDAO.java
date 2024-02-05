package com.example.demo.test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestDAO {
	
	@Autowired
	private SqlSession ss;
	
	public void getAll(HttpServletRequest req) {
		List<TestModel> tm = ss.getMapper(TestMapper.class).selectAll();
		System.out.println(tm.size());
		
		req.setAttribute("result", "done");
		
	}

}
