package com.example.demo.bm;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DirSetting;

@Service
public class BMDAO {

	private String uploadDir = DirSetting.getBusinessFolderDir();
	
	@Autowired
	private BmMapper bmMapper;
	
	public boolean signupBM(HttpServletRequest req, BusinessMember bm) {
		
		try {
			
			if(bmMapper.insertBM(bm)>0) {
				System.out.println("insertSuccess");
				String BusinessFolderDir = uploadDir + bm.getBm_id();
				Path BusinessFolderPath = Paths.get(BusinessFolderDir);
				Files.createDirectories(BusinessFolderPath);
				
				return true;
				
			}else {
				System.out.println("insertFailed");
				return false;
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	
	public boolean loginBM(HttpServletRequest req, BusinessMember bm) {
		
		try {
			
			List<BusinessMember> bms = bmMapper.getBM(bm);
			BusinessMember m = bms.get(0);
			HttpSession hs = req.getSession();
			
			if(bm.getBm_pw().equals(m.getBm_pw())) {
				hs.setAttribute("LoginMember", m);
								
				return true;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		
		return false;
	}
	
	
	
	public void logout(HttpServletRequest req) {
		req.getSession().invalidate();
	}
	
}
