package com.ikon.alexx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ikon.alexx.converters.LocationConverter; 
import com.ikon.alexx.model.LocationDTO;
import com.ikon.alexx.repository.LocationRepository;
import com.ikon.alexx.service.LocationService;


@Component
@Transactional
public class LocationServiceImp implements LocationService{

	@Autowired
	private LocationRepository locationRepo;
	
	@Autowired
	private LocationConverter locationConverter;
	
	@Override
	public void insert(LocationDTO location) {		
	locationRepo.save(locationConverter.toEntity(location));		
	}

	@Override
	public void update(LocationDTO location) {
		locationRepo.save(locationConverter.toEntity(location));		
	}

	@Override
	public void delete(String id) {
		locationRepo.delete(id);		
	}

	@Override
	public LocationDTO getById(String id) {		 
		return locationConverter.fromEntity(locationRepo.findOne(id));
	}

	@Override
	public List<LocationDTO> getAllLocations() {		
		return locationConverter.fromEntities(locationRepo.findAll());
	}

	@Override
	public List<LocationDTO> getLocationsByIds(List<String> ids) {	 
		return locationConverter.fromEntities(locationRepo.findAll(ids)); 
	}

}
