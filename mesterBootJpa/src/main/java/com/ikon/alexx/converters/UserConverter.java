package com.ikon.alexx.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikon.alexx.entity.User;
import com.ikon.alexx.model.UserDTO;
import com.ikon.alexx.repository.RoleRepository;

@Component
public class UserConverter extends BaseConverter<UserDTO, User>  {

	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public User toEntity(UserDTO pojo) {
		User entity = new User();
		entity.setId(pojo.getId());
		entity.setUserName(pojo.getUserName());
		entity.setPassword(pojo.getPassword());
		entity.setEmail(pojo.getEmail());
		entity.setIsEnable(pojo.getIsEnable());
		entity.setRole(roleRepo.getOne(pojo.getRoleId()));
		return entity;
	}

	@Override
	public UserDTO fromEntity(User entity) {
		UserDTO pojo = new UserDTO();
		pojo.setId(entity.getId());
		pojo.setUserName(entity.getUserName());
		pojo.setPassword(entity.getPassword());
		pojo.setEmail(entity.getEmail());
		pojo.setIsEnable(entity.getIsEnable()); 
		pojo.setRoleId(entity.getRole().getId());
		return pojo;
	}

}
