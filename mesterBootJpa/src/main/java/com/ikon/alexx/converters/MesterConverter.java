package com.ikon.alexx.converters;

import org.springframework.stereotype.Component;

import com.ikon.alexx.entity.Mester;
import com.ikon.alexx.model.MesterDTO;

@Component
public class MesterConverter  extends BaseConverter<MesterDTO, Mester>{

	@Override
	public Mester toEntity(MesterDTO pojo) {
		Mester entity = new Mester();
		entity.setId(pojo.getId());
		entity.setFirstName(pojo.getFirstName());
		entity.setLastName(pojo.getLastName());
		entity.setDescription(pojo.getDescription());
		entity.setAvgPrice(pojo.getAvgPrice());
		entity.setAvgRating(pojo.getAvgRating());
		return entity;
	}

	@Override
	public MesterDTO fromEntity(Mester entity) {
		MesterDTO pojo = new MesterDTO();
		pojo.setId(entity.getId());
		pojo.setFirstName(entity.getFirstName());
		pojo.setLastName(entity.getLastName());
		pojo.setDescription(entity.getDescription());
		pojo.setAvgPrice(entity.getAvgPrice());
		pojo.setAvgRating(entity.getAvgRating());	
		pojo.setUserId(entity.getUser().getId());
		return pojo;
	}

}
