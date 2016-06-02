package com.intern.alexx.repository;

import java.sql.SQLException;
import java.util.List;

import com.intern.alexx.model.User;

public interface UserRepository {

	User getUserByCredentials(String userName, String password);

	User getUserByUserName(String userName);

	User getUserById(String id);

	User getUserByNameAndEmail(String userName, String email);

	void insertUser(User user);

	void updateUser(User user);

	void updateUserDetails(User user);

	void updateUserEmail(User user);

	void deleteUser(String id);

	String getUserRole(int roleId);

	List<User> getAllUsers() throws SQLException;

}
