package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.bm.BusinessMember;
import com.example.demo.pm.PrivateMember;
import com.example.demo.ps.PSDAO;

@Controller
public class HomeController {
	
	@Autowired
	private PSDAO pdao;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(HttpServletRequest req) {
		
		
		if(req.getSession().getAttribute("LoginMember")!=null) {
			if(req.getSession().getAttribute("LoginMember") instanceof PrivateMember) {
				PrivateMember pm = (PrivateMember) req.getSession().getAttribute("LoginMember");
				if(pm.getPm_subscribe()==1) {
					pdao.getFileList(req);
					req.setAttribute("subPage", "storage/pmStorage.jsp");
				}else {
					req.setAttribute("subPage", "subscribe/privateSubscribe.jsp");
				}
				
			}else if(req.getSession().getAttribute("LoginMember") instanceof BusinessMember) {
				BusinessMember bm = (BusinessMember) req.getSession().getAttribute("LoginMember");
				if(bm.getBm_subscribe()==1) {
					req.setAttribute("subPage", "storage/bmStorage.jsp");
				}else {
					req.setAttribute("subPage", "subscribe/businessSubscribe.jsp");
				}
				
			}
		}else {
			req.setAttribute("subPage", "blank.jsp");
		}
	   
	   return "index";	
	}
}
