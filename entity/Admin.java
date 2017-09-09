package com.entity;

public class Admin {
		// TODO Auto-generated method stub
		private String aId;
		private String aPsd;	
		public Admin(String aId,String aPsd){
			this.aId = aId;
			this.aPsd = aPsd;
		}
		public String getaId() {
			return aId;
		}
		public void setaId(String aId) {
			this.aId = aId;
		}
		public String getaPsd() {
			return aPsd;
		}
		public void setaPsd(String aPsd) {
			this.aPsd = aPsd;
		}
}
