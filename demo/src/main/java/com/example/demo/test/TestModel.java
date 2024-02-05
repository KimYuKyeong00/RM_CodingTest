package com.example.demo.test;

public class TestModel {
	String test_char;
	int test_num;
	
	public TestModel() {
	}

	public TestModel(String test_char, int test_num) {
		super();
		this.test_char = test_char;
		this.test_num = test_num;
	}

	public String getTest_char() {
		return test_char;
	}

	public void setTest_char(String test_char) {
		this.test_char = test_char;
	}

	public int getTest_num() {
		return test_num;
	}

	public void setTest_num(int test_num) {
		this.test_num = test_num;
	}
	
	
}
