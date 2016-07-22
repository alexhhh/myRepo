package com.ikon.alexx.model;

public class AdminDTO {

	private String id;

	private String adminName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", adminName=" + adminName + " ]";
	}
}
