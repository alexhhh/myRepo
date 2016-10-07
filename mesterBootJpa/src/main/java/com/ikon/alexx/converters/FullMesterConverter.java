package com.ikon.alexx.converters;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikon.alexx.entity.Mester;
import com.ikon.alexx.model.FullMester;
import com.ikon.alexx.repository.UserRepository;

@Component
public class FullMesterConverter extends BaseConverter<FullMester, Mester> {

	@Autowired
	private LocationConverter locationConv;

	@Autowired
	private ContactConverter contactConv;

	@Autowired
	private UserRepository userRepo;

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
		entity.setContact(contactConv.toEntity(pojo.getContact()));
		entity.getContact().setMester(entity);
		entity.setLocation(locationConv.toEntity(pojo.getLocation()));
		entity.getLocation().setMester(entity);
		entity.setSpecialities(new HashSet<>(specConv.toEntities(pojo.getSpeciality())));
		entity.setUser(userRepo.getOne(pojo.getUserId()));
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
		pojo.setSpeciality(specConv.fromEntities(new ArrayList<>(entity.getSpecialities())));
		return pojo;
	}

}
