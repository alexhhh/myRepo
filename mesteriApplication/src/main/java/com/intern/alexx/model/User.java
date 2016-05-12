package com.intern.alexx.model;

 
import com.wordnik.swagger.annotations.ApiModel;

@ApiModel
public class User extends BaseModel {

	private String userName;
	private String password;
	private int roleId;
	private int isEnable;
	private String email;
	
	public User (String idUser, String userName, String password, String email,int roleId, int isEnable){
		super(idUser);
		this.userName=userName;
		this.password=password;
		this.roleId=roleId;
		this.isEnable=isEnable;
		this.setEmail(email);
		
	}
	
	 
	public User() {
		 
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
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int isEnable() {
		return isEnable;
	}

	public void setEnable(Integer integer) {
		this.isEnable = integer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userId="+ getId()+ " userName=" + userName + ", password=" + password + ", roleId=" + roleId + ", isEnable=" + isEnable
				+ ", email=" + email + "]";
	}

	 
 
}
