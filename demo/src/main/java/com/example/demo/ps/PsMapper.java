package com.example.demo.ps;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.pm.PrivateMember;

@Mapper
public interface PsMapper {
	
	abstract List<PrivateStorage> getPS(PrivateMember pm);
	abstract int insertPrivateFile(PrivateFile pf);
	abstract int updatePrivateStorage(PrivateStorage ps);
	abstract List<PrivateFile> getPF(PrivateMember pm);
	abstract List<PrivateFile> getPFBySerial(int pfl_serial);
	
}
