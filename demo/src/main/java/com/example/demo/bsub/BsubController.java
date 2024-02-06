package com.example.demo.bsub;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class BsubController {
	
	
	@Autowired
	BsubDAO bsubdao;
	
	@RequestMapping(value="/get.bcl", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody List<BusinessCommodityList> getBusinessCommodityList(HttpServletRequest req){
		return bsubdao.getBCL();
	}
	
	
	@RequestMapping(value="/get.bc", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody BusinessCommodityList getBusinessCommodity(HttpServletRequest req) {
		return bsubdao.getBC(req);
	}
	
	@RequestMapping(value="/bsub.subscribe", method=RequestMethod.POST)
	public String businessSubscribe(HttpServletRequest req) {
		if(bsubdao.businessSubscribe(req)) {
			req.setAttribute("subPage", "storage/bmStorage.jsp");
		}else {
			req.setAttribute("subPage", "subscribe/businessSubscribe.jsp");
		}
		
		return "index";
	}
//	
//	@RequestMapping(value="/go.puc", method=RequestMethod.POST)
//	public String goPmUpgradeCommodity(HttpServletRequest req) {
//		req.setAttribute("subPage", "subscribe/pmUpgradeCommodity.jsp");
//		return "index";
//	}
//	
//	@RequestMapping(value="/psub.upgrade", method=RequestMethod.POST)
//	public String pmUpgradeCommodity(HttpServletRequest req) {
//		if(psubdao.pmUpgradeCommodity(req)) {
//			pdao.getFileList(req);
//			req.setAttribute("subPage", "storage/pmStorage.jsp");
//		}else {
//			req.setAttribute("subPage", "subscribe/upgradeFailed.jsp");
//		}
//		return "index";
//	}
//	
//	
//	@RequestMapping(value="/go.pu", method=RequestMethod.POST)
//	public String goPmExtension(HttpServletRequest req) {
//		req.setAttribute("subPage", "subscribe/pmExtension.jsp");
//		return "index";
//	}
//	
//	@RequestMapping(value="/psub.extense", method=RequestMethod.POST)
//	public String pmExtension(HttpServletRequest req) {
//		if(psubdao.pmExtension(req)) {
//			pdao.getFileList(req);
//			req.setAttribute("subPage", "storage/pmStorage.jsp");
//		}else {
//			req.setAttribute("subPage", "subscribe/extensionFailed.jsp");
//		}
//		return "index";
//	}
//	
//	
//	@RequestMapping(value="/go.pas", method=RequestMethod.POST)
//	public String goPmAddStorage(HttpServletRequest req) {
//		req.setAttribute("subPage", "subscribe/pmAddStorage.jsp");
//		return "index";
//	}
//	
//	
//	@RequestMapping(value="/psub.expand",method=RequestMethod.POST)
//	public String pmAddStorage(HttpServletRequest req) {
//		if(psubdao.pmAddStorage(req)) {
//			pdao.getFileList(req);
//			req.setAttribute("subPage", "storage/pmStorage.jsp");
//		}else {
//			req.setAttribute("subPage", "subscribe/expandFailed.jsp");
//		}
//		return "index";
//	}

}
