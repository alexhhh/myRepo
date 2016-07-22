package com.ikon.alexx.service.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ikon.alexx.model.ClientDTO;
import com.ikon.alexx.model.MesterDTO;
import com.ikon.alexx.model.TokenDTO;
import com.ikon.alexx.model.UserDTO;
import com.ikon.alexx.converters.UserConverter;
import com.ikon.alexx.encryption.Encryption;
import com.ikon.alexx.entity.User;
import com.ikon.alexx.repository.RoleRepository;
import com.ikon.alexx.repository.UserRepository;
import com.ikon.alexx.service.AuthMailService;
import com.ikon.alexx.service.ClientService;
import com.ikon.alexx.service.MesterService;
import com.ikon.alexx.service.TokenService;
import com.ikon.alexx.service.UserService;

@Transactional
@Component
public class UserServiceImp implements UserService{

	@Autowired
	private Encryption encryption;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserConverter userConv;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private AuthMailService authMailService;
	
	@Autowired
	private MesterService mesterService;
	
	@Autowired
	private ClientService clientService;
	
	@Override
	public UserDTO getUserById(String id) {		 
		return userConv.fromEntity(userRepo.findOne(id));
	}

	@Override
	public UserDTO getUserByName(String userName) {		 
		return userConv.fromEntity(userRepo.findByUserName(userName));
	}

	@Override
	public String getUserRole(String roleId) {		 
		return roleRepo.getOne(roleId).getRole();
	}

	@Override
	public UserDTO getUser(String userName, String password) { 
		return userConv.fromEntity(userRepo.findByUserNameAndPassword(userName, password));
	}

	@Override
	public void insertUser(UserDTO user) {
		user.setPassword(encryption.encrypt(user.getPassword())); 
		userRepo.save(userConv.toEntity(user));
		TokenDTO token = tokenService.insert(user.getUserName());
		authMailService.AuthMailContent(user, token);
		if (getUserRole(user.getRoleId()) == "ROLE_MESTER") { 
			mesterService.insertMester(setMester(user));
		} else if (getUserRole(user.getRoleId()) == "ROLE_CLIENT") {
			clientService.insertClient(setClient(user) );
		}
		userRepo.save(userConv.toEntity(user));
	}

	@Override
	public void updateUserDetails(UserDTO user) {
		user.setPassword(encryption.encrypt(user.getPassword()));
		userRepo.save(userConv.toEntity(user));
	}

	@Override
	public void updateUserEmail(UserDTO user) {
		userRepo.save(userConv.toEntity(user));
	}

	@Override
	public void deleteUser(String userId, String role) {
		if (role == "ROLE_MESTER") {
			mesterService.deleteMester(userId);
		} else if (role == "ROLE_CLIENT") {
			clientService.deleteClient(userId);
		}
		userRepo.delete(userId);		
	}

	@Override
	public void activateUser(String tokenId) {
		TokenDTO token = tokenService.getById(tokenId);
		if (currentDate.before(token.getExpirationDate())) {
			User user = userRepo.findByUserName(token.getUserName());
			user.setIsEnable(1);
			 userRepo.save(user); 
		}	
	}

	@Override
	public List<UserDTO> getAllUsers() throws SQLException {		 
		return  userConv.fromEntities(userRepo.findAll());
	}

	@Override
	public void resetPasswordRequest(String userName, String email) {
		UserDTO user = userConv.fromEntity(userRepo.findByUserNameAndEmail(userName, email));
		if(user.getId()!= null){  
		 	authMailService.ResetPasswordMail(user, tokenService.insert(user.getUserName()));
		}
		}

	@Override
	public void updatePassword( TokenDTO token) {
		User user = userRepo.findByUserName(token.getUserName());		
		user.setPassword(encryption.encrypt(token.getPassword()));
		userRepo.save(user);
		tokenService.delete(token.getId());
	}
	
	private java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

	 private MesterDTO setMester(UserDTO user){
		 MesterDTO mester = new MesterDTO();
		 mester.setMesterUserId(user.getId());
		 return mester;		 
	 }
	 
	 private ClientDTO setClient(UserDTO user){
		 ClientDTO client = new ClientDTO();
		 client.setUserId(user.getId());
		 return client;
	 }
	  
	 
}
