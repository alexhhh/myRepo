package com.intern.alexx.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.intern.alexx.model.User;
import com.intern.alexx.repository.UserRepository;

public class UserDetailsServiceImp implements UserDetailsService {

	private static Logger log = LoggerFactory.getLogger(UserDetailsServiceImp.class);

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepo.getUserByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("username " + userName + " not found");
		}
		List<GrantedAuthority> authorities = buildUserAuthority(userRepo.getUserRole(user.getRoleId()));
		log.info("--Service --User Details -- loadUserByUsername -- authorities user by role.");
		return buildUserForAuthentication(user, authorities);
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		boolean enable = false;
		if (user.isEnable() == 1) {
			enable = true;
		} else {
			enable = false;
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), enable,
				true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(String userRole) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		setAuths.add(new SimpleGrantedAuthority(userRole));
		return new ArrayList<GrantedAuthority>(setAuths);
	}
}
