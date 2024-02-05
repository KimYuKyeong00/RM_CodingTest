package com.example.demo.bsub;

import java.sql.Date;

public class BusinessSubscribeOrder {
	private int order_serial;
	private String bm_id;
	private String order_kind;
	private Date order_date;
	private int total_pay;
	private int number_of_emp;
	private String commodity;
	private String capacity;
	private String period;
	
	public BusinessSubscribeOrder() {
	}

	public BusinessSubscribeOrder(int order_serial, String bm_id, String order_kind, Date order_date, int total_pay,
			int number_of_emp, String commodity, String capacity, String period) {
		super();
		this.order_serial = order_serial;
		this.bm_id = bm_id;
		this.order_kind = order_kind;
		this.order_date = order_date;
		this.total_pay = total_pay;
		this.number_of_emp = number_of_emp;
		this.commodity = commodity;
		this.capacity = capacity;
		this.period = period;
	}

	public int getOrder_serial() {
		return order_serial;
	}

	public void setOrder_serial(int order_serial) {
		this.order_serial = order_serial;
	}

	public String getBm_id() {
		return bm_id;
	}

	public void setBm_id(String bm_id) {
		this.bm_id = bm_id;
	}

	public String getOrder_kind() {
		return order_kind;
	}

	public void setOrder_kind(String order_kind) {
		this.order_kind = order_kind;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public int getTotal_pay() {
		return total_pay;
	}

	public void setTotal_pay(int total_pay) {
		this.total_pay = total_pay;
	}

	public int getNumber_of_emp() {
		return number_of_emp;
	}

	public void setNumber_of_emp(int number_of_emp) {
		this.number_of_emp = number_of_emp;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	
	
}
