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
		String sql = "INSERT INTO speciality (ID,NUME_SPECIALITY) " + "VALUES(?, ?)";
		
		//UUID id = UUID.randomUUID();
		 
		template.update(sql, speciality.getId(), speciality.getSpecialityName());
	}
	
 
	public void update(Speciality speciality) {
	 
		String sql = "UPDATE  speciality SET  NUME_SPECIALTY=?  WHERE id= ?";
		template.update(sql, speciality.getSpecialityName(),speciality.getId());
 
	}
	
	 
	public void delete(String idSpeciality) {
 
		String sql = "DELETE FROM speciality  WHERE id = ? ";
		template.update(sql, idSpeciality);
 
	}
	
	 
	public Speciality getByName(String specialityName) {
	 
		Speciality sepeciality= new Speciality();
		String sql = "SELECT * FROM speciality WHERE  NUME_SPECIALTY= ?";
		template.query(sql,   new Object[] {specialityName} , new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				getSpecialtyFromDB(sepeciality, rs);	 
			}});
	 return sepeciality;
	}

	public List<Speciality> getAllSpecialties() {
		List<Speciality > specialities	= new ArrayList<Speciality>();
		String sql = " SELECT * FROM specialty";
		specialities=template.queryForList(sql, Speciality.class);
		return specialities;
	}
	
	public void addSpecialityIntoDB(Speciality speciality, PreparedStatement ps) throws SQLException {
		ps.setString(1, speciality.getSpecialityName());
	}

	private Speciality getSpecialtyFromDB(Speciality speciality,ResultSet resultSet) throws SQLException {
		speciality.setId(resultSet.getString("id"));
		speciality.setSpecialityName(resultSet.getString("name_specialty"));
		return speciality;
	}

//	public String transactionalGetSpecialityID(Speciality speciality,Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException{
//		String specKey=null;
//		String sql = "SELECT speciality.id FROM speciality WHERE speciality_name = ?";
//		ps = conn.prepareStatement(sql);
//		ps.setString(1, speciality.getSpecialityName());
//		rs = ps.executeQuery();
//		if (rs.next()) {
//		specKey = rs.getString("id");
//		}
//		else {
//			sql = "INSERT INTO speciality (SPECIALITY_NAME) VALUES (?)";
//			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			ps.setString(1, speciality.getSpecialityName());
//			ps.executeUpdate();
//			rs = ps.getGeneratedKeys();
//			rs.next();
//			specKey = rs.getString(1);			 
//		}
//		return specKey;
//	}
}
