package com.entity;

public class Supplier {
	private int supId;
	private String supName;
	public int getSupId() {
		return supId;
	}
	public void setSupId(int supId) {
		this.supId = supId;
	}
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
	public Supplier(int supId, String supName) {
		this.supId = supId;
		this.supName = supName;
	}
	
}
