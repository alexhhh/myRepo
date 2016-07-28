package com.ikon.alexx.service;

import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;

import com.ikon.alexx.model.TokenDTO;
import com.ikon.alexx.model.UserDTO;

public interface UserService {

	UserDTO getUserById(String id);

	UserDTO getUserByName(String userName);

	String getUserRole(String roleId);

	UserDTO getUser(String userName, String password);

	void insertUser(UserDTO user) throws MessagingException;

	void updateUserDetails(UserDTO user);

	void updateUserEmail(UserDTO user);

	void deleteUser(String userId, String role);

	void activateUser(String tokenId);

	List<UserDTO> getAllUsers() throws SQLException;

	void resetPasswordRequest(String userName, String email);

	void updatePassword(TokenDTO token);

}
