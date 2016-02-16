/**
 * 
 */
package com.intern.alexx.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.intern.alexx.model.Speciality;

/**
 * @author malex
 *
 */
public interface SpecialityRepository {
	
	void insert(Speciality speciality);

	void update(Speciality speciality);

	void delete(Speciality speciality);

	Speciality getById(Speciality speciality);
	
	List<Speciality> getAllSpecialties();
	
	Integer transactionalGetSpecialityID(Speciality speciality,Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException;
}
