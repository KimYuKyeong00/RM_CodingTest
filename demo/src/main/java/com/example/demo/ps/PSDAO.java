package com.example.demo.ps;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DirSetting;
import com.example.demo.pm.PrivateMember;

@Service
public class PSDAO {
	
	@Autowired
	private PsMapper pMapper;
	
	private String pmFolderDir = DirSetting.getPrivateFolderDir();
	
	public PrivateStorage getPS(HttpServletRequest req) {
		try {
			PrivateMember pm = (PrivateMember) req.getSession().getAttribute("LoginMember");
			List<PrivateStorage> psl = pMapper.getPS(pm);
			return psl.get(0);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public boolean uploadFile(HttpServletRequest req, MultipartFile file) {
		try {
			
			PrivateMember pm = (PrivateMember) req.getSession().getAttribute("LoginMember");
			String folderDir = pmFolderDir + "/" + pm.getId();
			PrivateStorage ps = pMapper.getPS(pm).get(0);
			
			PrivateFile pf = new PrivateFile();
			pf.setFile_name(file.getOriginalFilename());
			pf.setFile_extension(file.getContentType());
			pf.setFile_size(file.getSize());
			pf.setPm_id(pm.getPm_id());
			
			Path filePath = Paths.get(folderDir).resolve(file.getOriginalFilename());
			Files.write(filePath,file.getBytes());
			
			ps.setStorage_status(ps.getStorage_status()+file.getSize());
			
			pMapper.insertPrivateFile(pf);
			pMapper.updatePrivateStorage(ps);
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;

	}
	
	
	public List<PrivateFile> getFileList(HttpServletRequest req) {
		try {
			PrivateMember pm = (PrivateMember) req.getSession().getAttribute("LoginMember");
			List<PrivateFile> pfl = pMapper.getPF(pm);
			req.setAttribute("pfl", pfl);
			return pfl;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	
	public void downloadFile(HttpServletRequest req, HttpServletResponse res) {
		try {
			PrivateMember pm = (PrivateMember) req.getSession().getAttribute("LoginMember");
			String folderDir = pmFolderDir + "/" + pm.getId();
			int pfl_serial = Integer.parseInt(req.getParameter("private_file_serial"));
			PrivateFile pf = pMapper.getPFBySerial(pfl_serial).get(0);
			Path filePath = Paths.get(folderDir).resolve(pf.getFile_name());
			File file = new File(filePath.toString());
			String fileName = pf.getFile_name();
			String downName = null;

			if (req.getHeader("user-agent").toLowerCase().contains("trident")) {
			    downName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			} else {
			    downName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}
			res.setHeader("Content-Disposition", "attachment;filename=\"" + downName + "\";");
			
			PrintWriter writer = res.getWriter();      
	        FileInputStream fileInputStream = new FileInputStream(file);
	        byte[] b = new byte[1024];
	        int data = 0;
	        while ((data = fileInputStream.read(b, 0, b.length)) != -1) {
	            writer.write(new String(b, 0, data, "ISO-8859-1"));
	        }
	        writer.close();
	        fileInputStream.close();

		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void deleteFile(HttpServletRequest req) {
		try {
			PrivateMember pm = (PrivateMember) req.getSession().getAttribute("LoginMember");
			String folderDir = pmFolderDir + "/" + pm.getId();
			int pfl_serial = Integer.parseInt(req.getParameter("private_file_serial"));
			PrivateFile pf = pMapper.getPFBySerial(pfl_serial).get(0);
			Path filePath = Paths.get(folderDir).resolve(pf.getFile_name());
			Files.deleteIfExists(filePath);
			pMapper.deletePF(pfl_serial);
		} catch (Exception e) {
			System.out.println(e.getMessage());		
		}
		

	}

}
