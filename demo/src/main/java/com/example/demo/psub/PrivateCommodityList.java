package com.example.demo.psub;

public class PrivateCommodityList {
	
	private String commodity_id;
	private int subscriber;
	private int service_price;
	private int storage_price;
	
	public PrivateCommodityList() {
	}
	
	public PrivateCommodityList(String commodity_id, int subscriber, int service_price, int storage_price) {
		super();
		this.commodity_id = commodity_id;
		this.subscriber = subscriber;
		this.service_price = service_price;
		this.storage_price = storage_price;
	}

	public String getCommodity_id() {
		return commodity_id;
	}

	public void setCommodity_id(String commodity_id) {
		this.commodity_id = commodity_id;
	}

	public int getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(int subscriber) {
		this.subscriber = subscriber;
	}

	public int getService_price() {
		return service_price;
	}

	public void setService_price(int service_price) {
		this.service_price = service_price;
	}

	public int getStorage_price() {
		return storage_price;
	}

	public void setStorage_price(int storage_price) {
		this.storage_price = storage_price;
	}
	
	

}
