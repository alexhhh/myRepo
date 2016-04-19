package com.intern.alexx.services.impl;

//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component; 
import org.springframework.transaction.annotation.Transactional;

import com.intern.alexx.model.User; 
import com.intern.alexx.repository.UserRepository;
import com.intern.alexx.services.UserService;

@Component
public class UserServiceImp implements UserService{

	
	@Autowired
	private UserRepository userRepo;
	
	@Transactional
	public User getUser(String userName, String password) {
		return	userRepo.getUserByCredentials(userName, password);
		 
	}

	
	@Transactional
	public User getUserByName(String userName ) {
		return	 userRepo.getUserByUserName(userName );
		 
	}


	@Transactional
	public void insertUser(User user) {
		userRepo.insertUser(user);
	}
	
	
	
	

}
