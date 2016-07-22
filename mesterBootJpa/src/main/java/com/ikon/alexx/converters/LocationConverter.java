package com.ikon.alexx.converters;

import org.springframework.stereotype.Component;

import com.ikon.alexx.entity.Location;
import com.ikon.alexx.model.LocationDTO;

@Component
public class LocationConverter extends BaseConverter<LocationDTO, Location> {

	@Override
	public Location toEntity(LocationDTO pojo) {
		Location entity = new Location();
		entity.setId(pojo.getId());
		entity.setLatitude(pojo.getLatitude());
		entity.setLongitude(pojo.getLongitude());
		entity.setLocation(pojo.getLocation());
		return entity;
	}

	@Override
	public LocationDTO fromEntity(Location entity) {
		LocationDTO pojo = new LocationDTO();
		pojo.setId(entity.getId());
		pojo.setLatitude(entity.getLatitude());
		pojo.setLongitude(entity.getLongitude());
		pojo.setLocation(entity.getLocation()); 
		return pojo;
	}
 
}
