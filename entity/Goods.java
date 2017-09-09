package com.entity;

public class Goods {
	private int g_id;
	private float g_price;
	private int g_num;
	private String g_name;
	
	public Goods(int g_id, String g_name, float g_price, int g_num) {
		this.g_id = g_id;
		this.g_price = g_price;
		this.g_num = g_num;
		this.g_name = g_name;
	}

	public float getG_price() {
		return g_price;
	}

	public void setG_price(float g_price) {
		this.g_price = g_price;
	}

	public int getG_id() {
		return g_id;
	}

	public void setG_id(int g_id) {
		this.g_id = g_id;
	}

	public int getG_num() {
		return g_num;
	}

	public void setG_num(int g_num) {
		this.g_num = g_num;
	}

	public String getG_name() {
		return g_name;
	}

	public void setG_name(String g_name) {
		this.g_name = g_name;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
