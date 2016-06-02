package com.intern.alexx.repository;

import java.util.List;

import com.intern.alexx.model.Location;

public interface LocationRepository {

	void insert(Location location);

	void update(Location location);

	void delete(String id);

	Location getById(String mesterId);

	List<Location> getAllLocations();

	List<Location> getLocationsByIds(List<String> ids);
}
