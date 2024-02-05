package com.example.demo.pm;

import java.sql.Date;
public class PrivateMember {
	
	private String pm_id;
	private String pm_pw;
	private String pm_email;
	private Date pm_date;
	private int pm_subscribe;
	
	public PrivateMember() {
	}

	
	public PrivateMember(String pm_id, String pm_pw, String pm_email, Date pm_date, int pm_subscribe) {
		super();
		this.pm_id = pm_id;
		this.pm_pw = pm_pw;
		this.pm_email = pm_email;
		this.pm_date = pm_date;
		this.pm_subscribe = pm_subscribe;
	}


	public String getPm_id() {
		return pm_id;
	}


	public void setPm_id(String pm_id) {
		this.pm_id = pm_id;
	}


	public String getPm_pw() {
		return pm_pw;
	}


	public void setPm_pw(String pm_pw) {
		this.pm_pw = pm_pw;
	}


	public String getPm_email() {
		return pm_email;
	}


	public void setPm_email(String pm_email) {
		this.pm_email = pm_email;
	}


	public Date getPm_date() {
		return pm_date;
	}


	public void setPm_date(Date pm_date) {
		this.pm_date = pm_date;
	}


	public int getPm_subscribe() {
		return pm_subscribe;
	}


	public void setPm_subscribe(int pm_subscribe) {
		this.pm_subscribe = pm_subscribe;
	}


	//
	public String getId() {
		return pm_id;
	}
	
	
	
	

}
