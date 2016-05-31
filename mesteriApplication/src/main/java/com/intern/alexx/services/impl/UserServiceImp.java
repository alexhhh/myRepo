package com.intern.alexx.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intern.alexx.encryption.Encryption;
import com.intern.alexx.model.Client;
import com.intern.alexx.model.Contact;
import com.intern.alexx.model.Mester;
import com.intern.alexx.model.Speciality;
import com.intern.alexx.model.Token;
import com.intern.alexx.model.User;
import com.intern.alexx.repository.ClientRepository; 
import com.intern.alexx.repository.UserRepository;
import com.intern.alexx.services.AuthMailService;
import com.intern.alexx.services.MesterService;
import com.intern.alexx.services.TokenService;
import com.intern.alexx.services.UserService;

@Component
public class UserServiceImp implements UserService {

	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImp.class);

	@Autowired
	private Encryption encryption;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private MesterService mesterService;

	@Autowired
	private TokenService tokenService;

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
	public void insertUser(User user) {
		user.setPassword(encryption.encrypt(user.getPassword()));
		userRepo.insertUser(user);
		Token token = tokenService.insert(user.getUserName());
		LOGGER.info("--before send mail ---" + user.toString() + "..." + token.toString() + "----");
		authMail.AuthMailContent(user, token);

		if (user.getRoleId() == 2) {
			Mester mester = new Mester();
			Contact contact = new Contact();
			List<Speciality> speciality = new ArrayList<>();
			mester.setId(user.getId());
			mester.setLocation("");
			mester.setMesterUserId(user.getId());
			contact.setEmail(user.getEmail());
			mester.setContact(contact);
			mester.setSpeciality(speciality);
			mesterService.insertMester(mester);
		} else if (user.getRoleId() == 3) {
			Client client = new Client();
			client.setId(user.getId());
			client.setClientUserId(user.getId());
			clientRepo.insertClient(client);
		}

	}

	@Transactional
	public void activateUser(String tokenId) {
		Token token = tokenService.getById(tokenId);
		LOGGER.info("--token ---" + token.toString() + "----");

		if (currentDate.before(token.getExpirationDate())) {
			User user = userRepo.getUserByUserName(token.getUserName());
			user.setEnable(1);
			LOGGER.info("--after ---" + user.toString() + "----");
			userRepo.updateUser(user); 
		}
	}
	
	@Transactional
	public void resetPasswordRequest(String userName, String email) {		 
		User user =userRepo.getUserByNameAndEmail(userName, email);
		if(user.getId()!= null){ 
			Token token = tokenService.insert(user.getUserName());
			LOGGER.info("--before send reset pass mail ---" + user.toString() + "..." + token.toString() + "----");
			authMail.ResetPasswordMail(user, token);
		}
	}

	@Transactional
	public void updatePassword(Token token) {
		User user = userRepo.getUserByUserName(token.getUserName());
		
		user.setPassword(encryption.encrypt(token.getPassword()));
		userRepo.updateUserDetails(user);
		tokenService.delete(token.getId());
	}

	@Transactional
	public String getUserRole(int roleId) {
		return userRepo.getUserRole(roleId);
	}

	@Transactional
	public void updateUserDetails(User user) {
		user.setPassword(encryption.encrypt(user.getPassword()));
		userRepo.updateUserDetails(user);
	}

	@Transactional
	public List<User> getAllUsers() throws SQLException {
		return userRepo.getAllUsers();
	} 

	@Transactional
	public void deleteUser(String userId, int role) {
		if (role == 2) {
			mesterService.deleteMester(userId);
		} else if (role == 3) {
			clientRepo.deleteClient(userId);
		}
		LOGGER.info("--userId---" + userId + "----");
		userRepo.deleteUser(userId);

	}

	private java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());


}
