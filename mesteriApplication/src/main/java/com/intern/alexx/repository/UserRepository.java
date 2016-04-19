package com.intern.alexx.repository;

import com.intern.alexx.model.User;

public interface UserRepository {

	public User getUserByCredentials(String userName, String password);
	
	public User getUserByUserName(String userName) ;

	public void insertUser(User user);

	public void updateUser(User user);

	public void deleteUser(String id);
	
	public String getUserRole (int roleId);

}
