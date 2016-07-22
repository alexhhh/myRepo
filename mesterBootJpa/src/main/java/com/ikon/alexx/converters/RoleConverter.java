package com.ikon.alexx.converters;

import com.ikon.alexx.entity.Role;
import com.ikon.alexx.model.RoleDTO;

public class RoleConverter implements Converter<RoleDTO, Role> {

	@Override
	public Role toEntity(RoleDTO pojo) {
		Role entity = new Role();
		entity.setId(pojo.getId());
		entity.setRole(pojo.getRole());
		return entity;
	}

	@Override
	public RoleDTO fromEntity(Role entity) {
		RoleDTO pojo = new RoleDTO();
		pojo.setId(entity.getId());
		pojo.setRole(entity.getRole());
		return pojo;
	}

}
