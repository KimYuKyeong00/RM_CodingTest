package com.example.demo.pm;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PmMapper {

	abstract int insertPM(PrivateMember pm);
	abstract List<PrivateMember> getPM(PrivateMember pm);
	
}
