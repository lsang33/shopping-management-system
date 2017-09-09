package com.entity;

public class SalesMan
{
	private String sId;
	private String sName;
	private String sPsd;

	public SalesMan(String sId, String sName, String sPsd) {
		this.sId = sId;
		this.sName = sName;
		this.sPsd = sPsd;
	}
	public SalesMan(String sName,String sPsd){
		this.sName=sName;
		this.sPsd=sPsd;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getsPsd() {
		return sPsd;
	}
	public void setsPsd(String sPsd) {
		this.sPsd = sPsd;
	}
	
}