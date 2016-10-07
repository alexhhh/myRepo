package com.ikon.alexx.service;

import java.util.List;

import com.ikon.alexx.model.LocationDTO;

public interface LocationService {

	void insert(LocationDTO location);

	void update(LocationDTO location);

	void delete(String mesterId);

	LocationDTO getById(String id);
	
	LocationDTO findByMesterId(String mesterId);

	List<LocationDTO> getAllLocations();

	List<LocationDTO> getLocationsByIds(List<String> ids);
}
