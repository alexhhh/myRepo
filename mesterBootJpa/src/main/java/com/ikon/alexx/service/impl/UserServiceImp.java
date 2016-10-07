package com.ikon.alexx.service.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ikon.alexx.converters.UserConverter;
import com.ikon.alexx.encryption.Encryption;
import com.ikon.alexx.entity.Mester;
import com.ikon.alexx.entity.Roles;
import com.ikon.alexx.entity.User;
import com.ikon.alexx.model.ClientDTO;
import com.ikon.alexx.model.TokenDTO;
import com.ikon.alexx.model.UserDTO;
import com.ikon.alexx.repository.RoleRepository;
import com.ikon.alexx.repository.UserRepository;
import com.ikon.alexx.service.AuthMailService;
import com.ikon.alexx.service.ClientService;
import com.ikon.alexx.service.MesterService;
import com.ikon.alexx.service.TokenService;
import com.ikon.alexx.service.UserService;

@Transactional
@Component
public class UserServiceImp implements UserService {

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

	private java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

	@Override
	public UserDTO getUserById(String id) {
		return userConv.fromEntity(userRepo.findOne(id));
	}

	@Override
	public UserDTO getUserByName(String userName) {
		User user = userRepo.findByUserName(userName);
		if (user == null) {
			throw new IllegalArgumentException("User is null");
		}
		return userConv.fromEntity(user);
	}

	@Override
	public String getUserRole(String roleId) {
		return roleRepo.getOne(roleId).getRole();
	}

	@Override
	public UserDTO getUser(String userName, String password) {
		return userConv.fromEntity(userRepo.findByUserNameAndPassword(userName, encryption.encrypt(password)));
	}

	@Override
	public void insertUser(UserDTO userDTO) throws MessagingException {
		userDTO.setPassword(encryption.encrypt(userDTO.getPassword()));
		User user = userRepo.save(createUserAndSetRole(userDTO));
		userDTO.setId(user.getId());
		setMesterOrClientForSpecificUser(userDTO, user);
	}

	@Override
	public void updateUserDetails(UserDTO user) {
		userRepo.save(setPasswordToUser(user.getUserName(), user.getPassword()));
	}

	@Override
	public void updateUserEmail(UserDTO user) {
		userRepo.save(userConv.toEntity(user));
	}

	@Override
	public void deleteUser(String userId) {
		if (Roles.ROLE_MESTER.toString().equals(userRepo.getOne(userId).getRole().getRole())) {
			mesterService.deleteMester(mesterService.getMesterByUserId(userId).getId());
		} else if (Roles.ROLE_CLIENT.toString().equals(userRepo.getOne(userId).getRole().getRole())) {
			clientService.deleteClient(clientService.getClientByUserId(userId).getId());
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
		return userConv.fromEntities(userRepo.findAll());
	}

	@Override
	public void resetPasswordRequest(String userName, String email) throws MessagingException {
		UserDTO user = userConv.fromEntity(userRepo.findByUserNameAndEmail(userName, email));
		if (user.getId() != null) {
			authMailService.resetPasswordMail(user, tokenService.insert(user.getUserName()));
		}
	}

	@Override
	public void updatePassword(TokenDTO token) {
		userRepo.save(setPasswordToUser(token.getUserName(), token.getPassword()));
	}

	private User setPasswordToUser(String userName, String pass) {
		User user = userRepo.findByUserName(userName);
		user.setPassword(encryption.encrypt(pass));
		return user;
	}

	private User createUserAndSetRole(UserDTO userDTO) {
		User user = userConv.toEntity(userDTO);
		user.setRole(roleRepo.getOne(userDTO.getRoleId()));
		return user;
	}

	private Mester setMester(User user) {
		Mester mester = new Mester();
		mester.setUser(user); 
		return mester;
	}

	private ClientDTO setClient(UserDTO user) {
		ClientDTO client = new ClientDTO();
		client.setUserId(user.getId());
		return client;
	}

	private void setMesterOrClientForSpecificUser(UserDTO userDTO, User user) throws MessagingException {
		authMailService.authMailContent(userDTO, tokenService.insert(userDTO.getUserName()));
		if (Roles.ROLE_MESTER.toString().equals(user.getRole().getRole())) {			 
			mesterService.insertMester(setMester(user));
		} else if (Roles.ROLE_CLIENT.toString().equals(user.getRole().getRole())) {
			clientService.insertClient(setClient(userDTO));
		} else {
			System.out.println("ceva nu ii ok pe aci ");
		}
	}

}
