package com.intern.alexx.model;

import java.sql.Date;

public class Token  extends BaseModel {
	 
	
	private String userName;
	private Date  expirationDate;
	
	 
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
	@Override
	public String toString() {
		return "Token [id=" + getId() + ", userName=" + userName + ", expirationDate=" + expirationDate + "]";
	}

}
