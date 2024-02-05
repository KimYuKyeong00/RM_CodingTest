package com.example.demo.pm;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.ps.PSDAO;

@Controller
public class PMController {
	
	@Autowired
	private PMDAO pdao;
	
	
	@Autowired
	private PSDAO psdao;
	
	@RequestMapping(value="/go.pm.signup", method=RequestMethod.GET)
	public String goToPMSignupPage(HttpServletRequest req) {
		req.setAttribute("subPage", "member/pmSignup.jsp");
		return "index";
	}
	
	@RequestMapping(value="/pm.signup", method=RequestMethod.POST)
	public String PMSignup(HttpServletRequest req, PrivateMember pm) {
		if(pdao.signupPM(req, pm)) {
			req.setAttribute("subPage", "member/signupSuccess.jsp");
		}else {
			req.setAttribute("subPage", "member/signupFailed.jsp");
		}
		return "index";
	}
	
	@RequestMapping(value="/go.pm.login",method=RequestMethod.GET)
	public String gologinPM(HttpServletRequest req, PrivateMember pm) {
		req.setAttribute("subPage", "member/pmLogin.jsp");
		return "index";
	}
	
	@RequestMapping(value="/pm.login", method=RequestMethod.POST)
	public String loginPM(HttpServletRequest req, PrivateMember pm) {
		if(pdao.loginPM(req, pm)) {
			PrivateMember m = (PrivateMember) req.getSession().getAttribute("LoginMember");
			if(m.getPm_subscribe()==0) {
				req.setAttribute("subPage", "subscribe/privateSubscribe.jsp");
			}else {
				psdao.getFileList(req);
				req.setAttribute("subPage", "storage/pmStorage.jsp");
			}
			
		}else {
			req.setAttribute("subPage", "member/loginFailed.jsp");
		}
		return "index";
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		pdao.logout(req);		
		return "index";
	}

}
