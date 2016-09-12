package com.ikon.alexx.service.impl;

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
import org.springframework.stereotype.Component;

import com.ikon.alexx.entity.User; 
import com.ikon.alexx.repository.UserRepository;

@Component
public class UserDetailsServiceImp implements UserDetailsService {

	private static Logger log = LoggerFactory.getLogger(UserDetailsServiceImp.class);

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("username " + userName + " not found");
		}
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRole().getRole());        //userRepo.getUserRole(user.getRoleId()));
		log.info("--Service --User Details -- loadUserByUsername -- authorities user by role "+ user.getRole().getRole() +"  . ");
		return buildUserForAuthentication(user, authorities);
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		boolean enable = false;
		if (user.getIsEnable() == 1) {
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
