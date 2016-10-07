package com.ikon.alexx.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue; 
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Admin {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String id;	
	
	private String adminName;
	
	@OneToOne
	private User user;
 
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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
		return "Admin [id=" + id + ", adminName=" + adminName + ", user=" + user + "]";
	}
}
