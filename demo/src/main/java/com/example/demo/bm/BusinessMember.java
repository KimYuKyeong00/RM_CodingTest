package com.example.demo.bm;

import java.sql.Date;

public class BusinessMember {
	
	private String bm_id;
	private String bm_pw;
	private String bm_email;
	private Date bm_date;
	private int bm_noe;
	private int bm_subscribe;
	
	public BusinessMember() {
	}

	public BusinessMember(String bm_id, String bm_pw, String bm_email, Date bm_date, int bm_noe, int bm_subscribe) {
		super();
		this.bm_id = bm_id;
		this.bm_pw = bm_pw;
		this.bm_email = bm_email;
		this.bm_date = bm_date;
		this.bm_noe = bm_noe;
		this.bm_subscribe = bm_subscribe;
	}


	public String getBm_id() {
		return bm_id;
	}

	public void setBm_id(String bm_id) {
		this.bm_id = bm_id;
	}

	public String getBm_pw() {
		return bm_pw;
	}

	public void setBm_pw(String bm_pw) {
		this.bm_pw = bm_pw;
	}

	public String getBm_email() {
		return bm_email;
	}

	public void setBm_email(String bm_email) {
		this.bm_email = bm_email;
	}

	public Date getBm_date() {
		return bm_date;
	}

	public void setBm_date(Date bm_date) {
		this.bm_date = bm_date;
	}

	public int getBm_noe() {
		return bm_noe;
	}

	public void setBm_noe(int bm_noe) {
		this.bm_noe = bm_noe;
	}

	public int getBm_subscribe() {
		return bm_subscribe;
	}

	public void setBm_subscribe(int bm_subscribe) {
		this.bm_subscribe = bm_subscribe;
	}

	//////////////////////////////////////////////////
	public String getId() {
		return bm_id;
	}
}
