package com.example.demo.pm;

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
public class PMDAO {
	
	private String uploadDir = DirSetting.getPrivateFolderDir();
	
	@Autowired
	private PmMapper pmMapper;
	
	public boolean signupPM(HttpServletRequest req, PrivateMember pm) {
		
		try {
			System.out.println("signupPM");
			if(pmMapper.insertPM(pm)>0) {
				System.out.println("insertSuccess");
				//개인 폴더 생성
				String privateFolderDir = uploadDir + pm.getPm_id();
				Path privateFolderPath = Paths.get(privateFolderDir);
				Files.createDirectories(privateFolderPath);
				
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
	
	
	public boolean loginPM(HttpServletRequest req, PrivateMember pm) {
		
		try {
			
			List<PrivateMember> pms = pmMapper.getPM(pm);
			PrivateMember m = pms.get(0);
			HttpSession hs = req.getSession();
			
			if(pm.getPm_pw().equals(m.getPm_pw())) {
				hs.setAttribute("LoginMember", m);
				
				System.out.println("로그인 성공");
				
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
