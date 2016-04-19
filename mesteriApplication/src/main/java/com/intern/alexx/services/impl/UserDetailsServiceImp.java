package com.intern.alexx.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import com.intern.alexx.model.User;
import com.intern.alexx.repository.UserRepository;


public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepo.getUserByUserName(userName);
		if (user == null)
			throw new UsernameNotFoundException("username " + userName + " not found");
		String role = userRepo.getUserRole(user.getRoleId());
		List<GrantedAuthority> authorities = buildUserAuthority(role);
		return buildUserForAuthentication(user, authorities);
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), user.isEnable(), true, true, true,
				authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(String userRole) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		setAuths.add(new SimpleGrantedAuthority(userRole));
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
		return result;
	}
}
