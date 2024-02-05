package com.example.demo.psub;

import java.sql.Date;

public class PrivateSubscribeOrder {

	private int order_serial;
	private String pm_id;
	private String order_kind;
	private Date order_date;
	private int total_pay;
	private String commodity;
	private String capacity;
	private String period;
	
	public PrivateSubscribeOrder() {
	}

	public PrivateSubscribeOrder(int order_serial, String pm_id, String order_kind, Date order_date, int total_pay,
			String commodity, String capacity, String period) {
		super();
		this.order_serial = order_serial;
		this.pm_id = pm_id;
		this.order_kind = order_kind;
		this.order_date = order_date;
		this.total_pay = total_pay;
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

	public String getPm_id() {
		return pm_id;
	}

	public void setPm_id(String pm_id) {
		this.pm_id = pm_id;
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
