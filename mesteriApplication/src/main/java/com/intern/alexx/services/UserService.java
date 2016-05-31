package com.intern.alexx.services;

import java.sql.SQLException;
import java.util.List;

import com.intern.alexx.model.Token;
import com.intern.alexx.model.User;

public interface UserService {

	public User getUser(String userName, String password);

	public User getUserByName(String userName);

	public void insertUser(User user);
	
	public void deleteUser(String userId, int role);
	
	public void updateUserDetails(User user);

	public String getUserRole(int roleId);

	public void activateUser(String tokenId);
	
	public List<User> getAllUsers()throws SQLException ;

	public void resetPasswordRequest(String userName, String email);

	public void updatePassword(Token token);

}
