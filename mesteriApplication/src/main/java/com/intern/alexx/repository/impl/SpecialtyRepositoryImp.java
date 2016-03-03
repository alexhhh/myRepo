package com.intern.alexx.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		Speciality sepeciality = new Speciality();
		String sql = "SELECT id FROM speciality WHERE  SPECIALITY_NAME= ?";
		template.query(sql, new Object[] { specialityName }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				Speciality sepeciality = new Speciality();
				sepeciality.setId(rs.getString("id"));
			}
		});
		return sepeciality.getId();
	}

	public List<Speciality> getAllSpecialties() {
		List<Speciality> specialities = new ArrayList<Speciality>();
		String sql = " SELECT * FROM specialty";
		specialities = template.queryForList(sql, Speciality.class);
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

}
