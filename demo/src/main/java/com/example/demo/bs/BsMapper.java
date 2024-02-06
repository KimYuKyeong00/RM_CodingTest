package com.example.demo.bs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.bm.BusinessMember;



@Mapper
public interface BsMapper {

	abstract List<BusinessStorage> getBS(BusinessMember bm);
	
//	abstract int insertPrivateFile(PrivateFile pf);
//	abstract int updatePrivateStorage(PrivateStorage ps);
//	abstract List<PrivateFile> getPF(PrivateMember pm);
//	abstract List<PrivateFile> getPFBySerial(int pfl_serial);
//	abstract int deletePF(int pfl_serial);
}
