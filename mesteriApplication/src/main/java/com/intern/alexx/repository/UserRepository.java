package com.intern.alexx.repository;

import java.sql.SQLException;
import java.util.List;

import com.intern.alexx.model.User;

public interface UserRepository {

	public User getUserByCredentials(String userName, String password);

	public User getUserByUserName(String userName);
	
	public User getUserById(String id);
	
	public User getUserByNameAndEmail(String userName , String email);

	public void insertUser(User user);

	public void updateUser(User user);

	public void updateUserDetails(User user);
	
	public void updateUserEmail(User user);

	public void deleteUser(String id);

	public String getUserRole(int roleId);
	
	public List<User> getAllUsers() throws SQLException;

}
