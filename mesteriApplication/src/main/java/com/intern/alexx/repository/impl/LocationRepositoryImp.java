package com.intern.alexx.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.Location;
import com.intern.alexx.repository.LocationRepository;

@Component
public class LocationRepositoryImp implements LocationRepository {

	private static Logger LOGGER = LoggerFactory.getLogger(LocationRepositoryImp.class);

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private NamedParameterJdbcTemplate namendTemplate;

	@Override
	public void insert(Location location) {
		location.setId(GUIDGenerator.generatedID());
		String sql = "INSERT INTO location (ID, MESTER_ID, LOCATION, LATITUDE, LONGITUDE) VALUES (?,?,?,?,?)";
		template.update(sql, new Object[] { location.getId(), location.getMesterId(), location.getLocation(),
				location.getLatitude(), location.getLongitude() });
	}

	@Override
	public void update(Location location) {
		String sql = "UPDATE location SET LOCATION=?, LATITUDE=?, LONGITUDE=? WHERE mester_id=?";
		template.update(sql, new Object[] { location.getLocation(), location.getLatitude(), location.getLongitude(),
				location.getMesterId() });
		LOGGER.info("update location ---" + location.toString());
	}

	@Override
	public void delete(String id) {
		String sql = "DELETE FROM location  WHERE mester_id = ? ";
		template.update(sql, id);
	}

	@Override
	public Location getById(String id) {
		String sql = "SELECT * from location where mester_id=?";
		Location location = new Location();
		template.query(sql, new Object[] { id }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				getLocationFromDB(location, rs);
			}
		});
		return location;
	}

	@Override
	public List<Location> getAllLocations() {
		List<Location> locations = new ArrayList<Location>();
		String sql = " SELECT * FROM location ";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<String, Object> row : rows) { 
			locations.add(locationRowMapper(row));
		} 	return locations;
	}
 
	public List<Location> getLocationsByIds(List<String> ids) {		 
		RowMapper<Location> rm = BeanPropertyRowMapper.newInstance(Location.class);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		String sql = " SELECT * FROM location WHERE mester_id IN (:ids)";
		return namendTemplate.query(sql, params, rm);		 
	}

	private Location getLocationFromDB(Location location, ResultSet resultSet) {
		try {
			location.setId(resultSet.getString("id"));
			location.setMesterId(resultSet.getString("mester_id"));
			location.setLocation(resultSet.getString("location"));
			location.setLatitude(resultSet.getDouble("latitude"));
			location.setLongitude(resultSet.getDouble("longitude"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return location;
	}

	private Location locationRowMapper(Map<String, Object> row) {
		Location location = new Location();
		location.setId((String) (row.get("id")));
		location.setMesterId((String) (row.get("mester_id")));
		location.setLocation((String) (row.get("location")));
		location.setLatitude((Double) (row.get("latitude")));
		location.setLongitude((Double) (row.get("longitude")));
		return location;
	}

}
