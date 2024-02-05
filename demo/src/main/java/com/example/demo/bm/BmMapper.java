package com.example.demo.bm;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BmMapper {
	
	abstract int insertBM(BusinessMember bm);
	abstract List<BusinessMember> getBM(BusinessMember bm);

}
