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
import com.intern.alexx.repository.UserRepository;
import com.intern.alexx.services.AuthMailService;
import com.intern.alexx.services.ClientService;
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
	private ClientService clientService;
	
	@Autowired
	private MesterService mesterService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private AuthMailService authMailService;

	@Transactional
	public User getUser(String userName, String password) { 
		LOGGER.info("---Service-- Get user by credentials");
		return userRepo.getUserByCredentials(userName, encryption.encrypt(password));
	}

	@Transactional
	public User getUserByName(String userName) {
		LOGGER.info("---Service-- Get user by name:" +userName);
		return userRepo.getUserByUserName(userName);
	}

	@Transactional
	public void insertUser(User user) {
		user.setPassword(encryption.encrypt(user.getPassword()));
		LOGGER.info("---Service--Inser User :"+ user.toString());
		userRepo.insertUser(user);
		Token token = tokenService.insert(user.getUserName());
		authMailService.AuthMailContent(user, token);
		if (user.getRoleId() == 2) { 
			mesterService.insertMester(setMester(user));
		} else if (user.getRoleId() == 3) {
			clientService.insertClient(setClient(user));
		}

	}

	@Transactional
	public void activateUser(String tokenId) {
		Token token = tokenService.getById(tokenId);
		if (currentDate.before(token.getExpirationDate())) {
			User user = userRepo.getUserByUserName(token.getUserName());
			user.setEnable(1);
			LOGGER.info("--Service -- Activate User: " + user.toString() + "----");
			userRepo.updateUser(user); 
		}
	}
	
	@Transactional
	public void resetPasswordRequest(String userName, String email) {		 
		User user =userRepo.getUserByNameAndEmail(userName, email);
		if(user.getId()!= null){ 
			Token token = tokenService.insert(user.getUserName());
			LOGGER.info("--Service--Send reset password mail to user: " + user.toString() + "  with token: " + token.toString() + "----");
			authMailService.ResetPasswordMail(user, token);
		}
	}

	@Transactional
	public void updatePassword(Token token) {
		User user = userRepo.getUserByUserName(token.getUserName());		
		user.setPassword(encryption.encrypt(token.getPassword()));
		userRepo.updateUserDetails(user);
		tokenService.delete(token.getId());
		LOGGER.info("---Service-- Update user password by token");

	}

	@Transactional
	public String getUserRole(int roleId) {
		LOGGER.info("---Service-- Get user role");
		return userRepo.getUserRole(roleId);
	}

	@Transactional
	public void updateUserDetails(User user) {
		user.setPassword(encryption.encrypt(user.getPassword()));
		userRepo.updateUserDetails(user);
		LOGGER.info("---Service-- Update user password");
	}

	@Transactional
	public List<User> getAllUsers() throws SQLException {
		LOGGER.info("---Service-- Get all users");
		return userRepo.getAllUsers();
	} 

	@Transactional
	public void deleteUser(String userId, int role) {
		if (role == 2) {
			mesterService.deleteMester(userId);
		} else if (role == 3) {
			clientService.deleteClient(userId);
		}
		LOGGER.info("---Service--Delete user with id:" + userId + "----");
		userRepo.deleteUser(userId);
	}

    @Transactional
	public User getUserById(String id) {
    	LOGGER.info("--Service-- Get user with id: "+ id);
		return userRepo.getUserById(id);
	}
    
	@Transactional
	public void updateUserEmail(User user) {
		LOGGER.info("--Service-- Update email for user: "+ user.toString());
		userRepo.updateUserEmail(user);		
	}
	
    private Mester setMester(User user){
    	Mester mester = new Mester();
		Contact contact = new Contact();
		List<Speciality> speciality = new ArrayList<>();
		mester.setId(user.getId());
		mester.setLocation("");
		mester.setMesterUserId(user.getId());
		contact.setEmail(user.getEmail());
		mester.setContact(contact);
		mester.setSpeciality(speciality);
		return mester;
    }
    
    private Client setClient(User user){
    	Client client = new Client();
		client.setId(user.getId());
		client.setClientUserId(user.getId());		
    	return client;
    }
    
	private java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

}
