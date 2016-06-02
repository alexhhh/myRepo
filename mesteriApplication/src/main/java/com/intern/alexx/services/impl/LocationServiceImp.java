package com.intern.alexx.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intern.alexx.model.Location;
import com.intern.alexx.repository.LocationRepository;
import com.intern.alexx.services.LocationService;

@Component
public class LocationServiceImp implements LocationService {

	private static Logger log = LoggerFactory.getLogger(LocationServiceImp.class);
			
	@Autowired
	private LocationRepository locationRepo;

	@Transactional
	public void insert(Location location) {
		locationRepo.insert(location);
		log.info("--Service-- Insert location "+location.toString());
	}

	@Transactional
	public void update(Location location) {
		locationRepo.update(location);
		log.info("--Service-- Update location "+location.toString());
	}

	@Transactional
	public void delete(String id) {
		locationRepo.delete(id);
		log.info("--Service-- Delete location ");
	}

	@Transactional
	public Location getById(String mesterId) {
		return locationRepo.getById(mesterId);
	}

	@Transactional
	public List<Location> getAllLocations() {
		log.info("--Service-- Get all locations ");
		return locationRepo.getAllLocations();
	}

	@Transactional
	public List<Location> getLocationsByIds(List<String> ids) {	
		log.info("--Service-- Get locations by ids");
		return locationRepo.getLocationsByIds(ids);
	}

}
