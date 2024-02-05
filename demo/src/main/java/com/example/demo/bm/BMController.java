package com.example.demo.bm;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BMController {
	
	@Autowired
	private BMDAO bdao;
	
	@RequestMapping(value="go.bm.signup", method=RequestMethod.GET)
	public String goBmSignup(HttpServletRequest req) {
		req.setAttribute("subPage", "member/bmSignup.jsp");
		return "index";
	}
	
	@RequestMapping(value="bm.signup", method=RequestMethod.POST)
	public String bmSignup(HttpServletRequest req, BusinessMember bm) {
		
		if(bdao.signupBM(req, bm)) {
			req.setAttribute("subPage", "member/signupSuccess.jsp");
		}else {
			req.setAttribute("subPage", "member/signupFailed.jsp");
		}
		return "index";
	}
	
	@RequestMapping(value="go.bm.login", method=RequestMethod.GET)
	public String goBmLogin(HttpServletRequest req) {
		req.setAttribute("subPage", "member/bmLogin.jsp");
		return "index";
	}
	
	@RequestMapping(value="bm.login", method=RequestMethod.POST)
	public String bmLogin(HttpServletRequest req, BusinessMember bm) {
		if(bdao.loginBM(req, bm)) {
			req.setAttribute("subPage", "member/loginSuccess.jsp");
		}else {
			req.setAttribute("subPage", "member/loginFailed.jsp");
		}
		return "index";
	}

}
