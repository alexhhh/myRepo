package com.ikon.alexx.converters;

import org.springframework.stereotype.Component;

import com.ikon.alexx.entity.Admin;
import com.ikon.alexx.model.AdminDTO;

@Component
public class AdminConverter implements Converter<AdminDTO, Admin> {

	@Override
	public Admin toEntity(AdminDTO pojo) {
		Admin entity= new Admin();
		entity.setId(pojo.getId());
		entity.setAdminName(pojo.getAdminName());
		return entity;
	}

	@Override
	public AdminDTO fromEntity(Admin entity) {
		AdminDTO pojo = new AdminDTO();
		pojo.setId(entity.getId());
		pojo.setAdminName(entity.getAdminName());
		return pojo;
	}

}
