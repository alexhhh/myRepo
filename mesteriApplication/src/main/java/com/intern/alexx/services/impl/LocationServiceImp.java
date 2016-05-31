package com.intern.alexx.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intern.alexx.model.Location;
import com.intern.alexx.repository.LocationRepository;
import com.intern.alexx.services.LocationService;

@Component
public class LocationServiceImp implements LocationService {

	@Autowired
	private LocationRepository locationRepo;

	@Transactional
	public void insert(Location location) {
		locationRepo.insert(location);
	}

	@Transactional
	public void update(Location location) {
		locationRepo.update(location);
	}

	@Transactional
	public void delete(String id) {
		// in mester service
	}

	@Transactional
	public Location getById(String mesterId) {
		return locationRepo.getById(mesterId);
	}

	@Transactional
	public List<Location> getAllLocations() {
		return locationRepo.getAllLocations();
	}

	@Transactional
	public List<Location> getLocationsByIds(List<String> ids) {		 
		return locationRepo.getLocationsByIds(ids);
	}

}
