package com.example.demo.bs;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class BSController {

	
	@Autowired
	private BSDAO bdao;
	
	
	@RequestMapping(value="/get.bs", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody BusinessStorage getBsJson(HttpServletRequest req) {
		return bdao.getBS(req);
	}
}
