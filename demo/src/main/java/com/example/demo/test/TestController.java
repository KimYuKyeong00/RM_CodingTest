package com.example.demo.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

	@Autowired
	private TestDAO tdao;
	
	@RequestMapping(value="/select.all", method=RequestMethod.GET)
	public String getAllData(HttpServletRequest req) {
		
		tdao.getAll(req);
		
		return "index";
	}
}
