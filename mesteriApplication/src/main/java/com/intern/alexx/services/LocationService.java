package com.intern.alexx.services;

import java.util.List;

import com.intern.alexx.model.Location;

public interface LocationService {

	 void insert(Location location);

	 void update(Location location);

	 void delete(String mesterId );

	 Location getById (String mesterId);
	
	 List<Location> getAllLocations() ;

	 List<Location> getLocationsByIds(List<String> ids);
}

