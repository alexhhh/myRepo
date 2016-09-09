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
import com.ikon.alexx.entity.Roles;
import com.ikon.alexx.entity.User;
import com.ikon.alexx.model.ClientDTO;
import com.ikon.alexx.model.MesterDTO;
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

	@Override
	public UserDTO getUserById(String id) {
		return userConv.fromEntity(userRepo.findOne(id));
	}

	@Override
	public UserDTO getUserByName(String userName) {
		User user = userRepo.findByUserName(userName);
		if (user != null) {
			return userConv.fromEntity(user);
		} else
			return null;

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
		User user = userConv.toEntity(userDTO);
		user.setRole(roleRepo.getOne(userDTO.getRoleId()));
		user = userRepo.save(user);
		userDTO.setId(user.getId());
		setBlackSpecificUser(userDTO, user) ;		
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
			TokenDTO token = tokenService.insert(user.getUserName());
			authMailService.resetPasswordMail(user, token);
		}
	}

	@Override
	public void updatePassword(TokenDTO token) {
		User user = userRepo.findByUserName(token.getUserName());
		user.setPassword(encryption.encrypt(token.getPassword()));
		userRepo.save(user);
	}

	private java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

	private MesterDTO setMester(UserDTO user) {
		MesterDTO mester = new MesterDTO();
		mester.setUserId(user.getId());
		return mester;
	}

	private ClientDTO setClient(UserDTO user) {
		ClientDTO client = new ClientDTO();
		client.setUserId(user.getId());
		return client;
	}

	private void setBlackSpecificUser(UserDTO userDTO,User user) throws MessagingException {
		TokenDTO token = tokenService.insert(userDTO.getUserName());
		authMailService.authMailContent(userDTO, token);
		if (Roles.ROLE_MESTER.toString().equals(user.getRole().getRole())) {
			mesterService.insertMester(setMester(userDTO));
		} else if (Roles.ROLE_CLIENT.toString().equals(user.getRole().getRole())) {
			clientService.insertClient(setClient(userDTO));
		} else {
			System.out.println("ceva nu ii ok pe aci ");
		}
	}
	
}
