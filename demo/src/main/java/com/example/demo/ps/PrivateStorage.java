package com.example.demo.ps;

import java.sql.Date;

public class PrivateStorage {
	
	private String pm_id;
	private String commodity_id;
	private Date expire_date;
	private long max_storage;
	private long storage_status;
	
	public PrivateStorage() {
	}

	public PrivateStorage(String pm_id, String commodity_id, Date expire_date, long max_storage, long storage_status) {
		super();
		this.pm_id = pm_id;
		this.commodity_id = commodity_id;
		this.expire_date = expire_date;
		this.max_storage = max_storage;
		this.storage_status = storage_status;
	}

	public String getPm_id() {
		return pm_id;
	}

	public void setPm_id(String pm_id) {
		this.pm_id = pm_id;
	}

	public String getCommodity_id() {
		return commodity_id;
	}

	public void setCommodity_id(String commodity_id) {
		this.commodity_id = commodity_id;
	}

	public Date getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(Date expire_date) {
		this.expire_date = expire_date;
	}

	public long getMax_storage() {
		return max_storage;
	}

	public void setMax_storage(long max_storage) {
		this.max_storage = max_storage;
	}

	public long getStorage_status() {
		return storage_status;
	}

	public void setStorage_status(long storage_status) {
		this.storage_status = storage_status;
	}
	
	
}
