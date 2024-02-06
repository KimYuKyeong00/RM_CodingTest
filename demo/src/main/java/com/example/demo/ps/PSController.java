package com.example.demo.ps;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class PSController {
	
	@Autowired
	private PSDAO pdao;
	
	
	@RequestMapping(value="/get.ps", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody PrivateStorage getPsJson(HttpServletRequest req) {
		return pdao.getPS(req);
	}
	
	
	
	@RequestMapping(value="/ps.upload", method=RequestMethod.POST)
	public String uploadFile(HttpServletRequest req,@RequestParam("file")MultipartFile mf) {
		pdao.uploadFile(req, mf);
		pdao.getFileList(req);
		req.setAttribute("subPage", "storage/pmStorage.jsp");
		return "index";
	}
	
	
	@RequestMapping(value="/ps.download", method=RequestMethod.POST)
	public String downloadFile(HttpServletRequest req, HttpServletResponse res) {
		pdao.downloadFile(req,res);
		pdao.getFileList(req);
		req.setAttribute("subPage", "storage/pmStorage.jsp");
		return "index";
	}
	
	
	@RequestMapping(value="/ps.delete", method=RequestMethod.POST)
	public String deleteFile(HttpServletRequest req) {
		pdao.deleteFile(req);
		pdao.getFileList(req);
		req.setAttribute("subPage","storage/pmStorage.jsp");
		return "index";
	}

	
	
}
