package com.ikon.alexx.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue; 
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Token {

	
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid2")
	private String id;

	private String userName;
	private Date  expirationDate;
	private String password;
	
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
		return "Token [id=" + id + ", userName=" + userName + ", expirationDate=" + expirationDate + ", password="
				+ password + "]";
	}
}
