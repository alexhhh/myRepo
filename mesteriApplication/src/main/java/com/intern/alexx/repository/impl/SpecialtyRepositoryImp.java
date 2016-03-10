package com.intern.alexx.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;
 
import com.intern.alexx.model.Speciality;
import com.intern.alexx.repository.SpecialityRepository;

@Component
public class SpecialtyRepositoryImp implements SpecialityRepository {

	@Autowired
	private JdbcTemplate template;

	public void insert(Speciality speciality) {
		speciality.setId(GUIDGenerator.generatedID());
		String sql = "INSERT INTO speciality (ID,SPECIALITY_NAME) " + "VALUES(?, ?)";
		template.update(sql, speciality.getId(), speciality.getSpecialityName());
	}

	public void update(Speciality speciality) {
		String sql = "UPDATE  speciality SET  SPECIALITY_NAME=?  WHERE id= ?";
		template.update(sql, speciality.getSpecialityName(), speciality.getId());
	}

	public void delete(String idSpeciality) {
		String sql = "DELETE FROM speciality  WHERE id = ? ";
		template.update(sql, idSpeciality);

	}

	public Speciality getByName(String specialityName) {
		Speciality sepeciality = new Speciality();
		String sql = "SELECT * FROM speciality WHERE  SPECIALITY_NAME= ?";
		template.query(sql, new Object[] { specialityName }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				getSpecialtyFromDB(sepeciality, rs);
			}
		});
		return sepeciality;
	}

	public String getSpecialityIdByName(String specialityName) {
		String sql = "SELECT id FROM speciality WHERE  SPECIALITY_NAME= ?";
		return template.queryForObject(sql, new Object[] { specialityName }, String.class);

	}

	public List<Speciality> getAllSpecialties() throws SQLException {
		List<Speciality> specialities = new ArrayList<Speciality>();
		String sql = " SELECT * FROM speciality ";
 
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<String, Object> row : rows) {
			Speciality speciality = new Speciality();
			specialityRowMapper(speciality, row);
			specialities.add(speciality);
		}
		return specialities;
	}

	
	public List<Speciality> getAllMesterSpecialities(String idMester) throws SQLException {
		List<Speciality> specialities = new ArrayList<Speciality>();
		String sql = " SELECT speciality_name FROM speciality as s JOIN mester_has_speciality as mhs JOIN mester as m ON m.id = mhs.id_mester AND s.id = mhs.id_speciality WHERE m.id=?";
		List<Map<String, Object>> rows = template.queryForList(sql, idMester);
		for (Map<String, Object> row : rows) {
			Speciality speciality = new Speciality();
			specialityRowMapper(speciality, row);
			specialities.add(speciality);
		}
		return specialities;
	}

	
	
	public void addSpecialityIntoDB(Speciality speciality, PreparedStatement ps) throws SQLException {
		ps.setString(1, speciality.getSpecialityName());
	}

	private Speciality getSpecialtyFromDB(Speciality speciality, ResultSet resultSet) throws SQLException {
		speciality.setId(resultSet.getString("id"));
		speciality.setSpecialityName(resultSet.getString("SPECIALITY_NAME"));
		return speciality;
	}
	private Speciality specialityRowMapper(Speciality speciality, Map<String, Object> row) throws SQLException {
		speciality.setId((String) (row.get("id")));
		speciality.setSpecialityName( ((String) (row.get("speciality_name"))));
		return speciality;
	}

}
