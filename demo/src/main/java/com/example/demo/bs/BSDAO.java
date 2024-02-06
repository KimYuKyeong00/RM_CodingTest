package com.example.demo.bs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.example.demo.DirSetting;
import com.example.demo.bm.BusinessMember;


@Service
public class BSDAO {
	
	@Autowired
	private BsMapper bMapper;
	
//	private String bmFolderDir = DirSetting.getBusinessFolderDir();
	
	public BusinessStorage getBS(HttpServletRequest req) {
		try {
			
			BusinessMember bm = (BusinessMember) req.getSession().getAttribute("LoginMember");
			List<BusinessStorage> bsl = bMapper.getBS(bm);
			return bsl.get(0);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
