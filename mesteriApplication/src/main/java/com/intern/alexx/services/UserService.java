package com.intern.alexx.services;

import java.sql.SQLException;
import java.util.List;

import com.intern.alexx.model.Token;
import com.intern.alexx.model.User;

public interface UserService {

	User getUserById(String id);

	User getUserByName(String userName);

	String getUserRole(int roleId);

	User getUser(String userName, String password);

	void insertUser(User user);

	void updateUserDetails(User user);

	void updateUserEmail(User user);

	void deleteUser(String userId, int role);

	void activateUser(String tokenId);

	List<User> getAllUsers() throws SQLException;

	void resetPasswordRequest(String userName, String email);

	void updatePassword(Token token);

}
