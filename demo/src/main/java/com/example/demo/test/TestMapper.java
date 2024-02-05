package com.example.demo.test;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {

	public abstract List<TestModel> selectAll();
	
}
