package com.example.demo.bs;

import java.sql.Date;

public class BusinessStorage {
	
	private String bm_id;
	private String commodity_id;
	private Date expire_date;
	private int max_user;
	private long max_storage;
	private long storage_status;
	
	public BusinessStorage() {
	}

	public BusinessStorage(String bm_id, String commodity_id, Date expire_date, int max_user, long max_storage,
			long storage_status) {
		super();
		this.bm_id = bm_id;
		this.commodity_id = commodity_id;
		this.expire_date = expire_date;
		this.max_user = max_user;
		this.max_storage = max_storage;
		this.storage_status = storage_status;
	}

	public String getBm_id() {
		return bm_id;
	}

	public void setBm_id(String bm_id) {
		this.bm_id = bm_id;
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

	public int getMax_user() {
		return max_user;
	}

	public void setMax_user(int max_user) {
		this.max_user = max_user;
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
