package com.intern.alexx.model;

public class Admin extends BaseModel{

	private String adminUserId;
	private String adminName;
	
	
	public String getAdminUserId() {
		return adminUserId;
	}
	public void setAdminUserId(String adminUserId) {
		this.adminUserId = adminUserId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	@Override
	public String toString() {
		return "Admin [ adminId="+ getId() +"adminUserId=" + adminUserId + ", adminName=" + adminName + "]";
	}
	
	
	
	
}
