package com.ikon.alexx.model;

public class UserDTO {

	private String id;
	private String userName;
	private String password;
	private int isEnable;
	private String email;
	private String roleId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", isEnable=" + isEnable
				+ ", email=" + email + ", roleId=" + roleId + "]";
	}

	 
}
