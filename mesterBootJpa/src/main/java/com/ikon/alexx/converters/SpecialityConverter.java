package com.ikon.alexx.converters;

import org.springframework.stereotype.Component;

import com.ikon.alexx.entity.Speciality;
import com.ikon.alexx.model.SpecialityDTO;

@Component
public class SpecialityConverter extends BaseConverter<SpecialityDTO, Speciality> {

	@Override
	public Speciality toEntity(SpecialityDTO pojo) {
		Speciality entity = new Speciality();
		entity.setId(pojo.getId());
		entity.setSpecialityName(pojo.getSpecialityName());
		return entity;
	}

	@Override
	public SpecialityDTO fromEntity(Speciality entity) {
		SpecialityDTO pojo = new SpecialityDTO();
		pojo.setId(entity.getId());
		pojo.setSpecialityName(entity.getSpecialityName());
		// BeanUtils.copyProperties(entity, pojo);
		return pojo;
	}

}
