package com.intern.alexx.model;

import java.sql.Date;

public class Token  extends BaseModel {
	 
	
	private String userName;
	private Date  expirationDate;
	private String password;
	
	 
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Token [userName=" + userName + ", expirationDate=" + expirationDate + ", password=" + password + "]";
	}
 
}
