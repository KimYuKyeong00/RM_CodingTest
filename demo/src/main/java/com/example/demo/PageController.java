package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
	
	@RequestMapping(value="go.signup", method=RequestMethod.GET)
	public String goSignup(HttpServletRequest req) {	
		req.setAttribute("subPage", "member/signup.jsp");
		return "index";
	}
	
	
	@RequestMapping(value="go.login", method=RequestMethod.GET)
	public String goLogin(HttpServletRequest req) {
		req.setAttribute("subPage", "member/login.jsp");
		return "index";
	}
	

	

}
