package com.ikon.alexx.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikon.alexx.entity.Mester;
import com.ikon.alexx.model.FullMester; 

@Component
public class FullMesterConverter extends BaseConverter<FullMester, Mester> {

	@Autowired
	private LocationConverter locationConv;
	
	@Autowired
	private ContactConverter contactConv;
	
	@Autowired
	private SpecialityConverter specConv;
	
	@Override
	public Mester toEntity(FullMester pojo) {
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
	public FullMester fromEntity(Mester entity) {
		FullMester pojo = new FullMester();
		pojo.setId(entity.getId());
		pojo.setFirstName(entity.getFirstName());
		pojo.setLastName(entity.getLastName());
		pojo.setDescription(entity.getDescription());
		pojo.setAvgPrice(entity.getAvgPrice());
		pojo.setAvgRating(entity.getAvgRating());	
		pojo.setUserId(entity.getUser().getId());
		pojo.setContact(contactConv.fromEntity(entity.getContact()));
		pojo.setLocation(locationConv.fromEntity(entity.getLocation()));
		pojo.setSpeciality(specConv.fromEntities(entity.getSpecialities()));
		return pojo; 
	}

}
