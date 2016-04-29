package com.intern.alexx.services.impl;

 
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intern.alexx.encryption.Encryption; 
import com.intern.alexx.model.Token;
import com.intern.alexx.model.User;
import com.intern.alexx.repository.TokenRepository;
import com.intern.alexx.repository.UserRepository;
import com.intern.alexx.services.AuthMailService;
import com.intern.alexx.services.UserService;

@Component
public class UserServiceImp implements UserService {

	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImp.class);
	
	@Autowired
	private Encryption encryption;
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TokenRepository tokenRepo;

	@Autowired
	private AuthMailService authMail;

	@Transactional
	public User getUser(String userName, String password) {
		String newPassword = encryption.encrypt(password);
		return userRepo.getUserByCredentials(userName, newPassword);
	}

	@Transactional
	public User getUserByName(String userName) {
		return userRepo.getUserByUserName(userName);
	}

	@Transactional
	public void insertUser(User user)  {
		user.setPassword(encryption.encrypt(user.getPassword()));
		userRepo.insertUser(user);
		Token token = tokenRepo.insert(user.getUserName());
		LOGGER.info("--before send mail ---" + user.toString() + "..." + token.toString() + "----");
		authMail.AuthMailContent(user, token);
	}

	@Transactional
	public void activateUser(String tokenId) {
		Token token = tokenRepo.getById(tokenId);
		LOGGER.info("--token ---" + token.toString() + "----");

		if (currentDate.before(token.getExpirationDate())) {
			User user = userRepo.getUserByUserName(token.getUserName());
			user.setEnable(true);
			LOGGER.info("--after ---" + user.toString() + "----");
			userRepo.updateUser(user);
		}
	}

	private java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

	@Transactional
	public String getUserRole (int  roleId) { 
		return	userRepo.getUserRole(roleId);
	}
 
}
