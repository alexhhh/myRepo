/**
 * 
 */
package com.intern.alexx.repository;

 
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

	void delete(String idSpeciality);

	public String getSpecialityIdByName(String specialityName);
	
	public Speciality getByName(String specialityName);
	
	public List<Speciality> getAllMesterSpecialities(String idMester) throws SQLException;
	
	public List<Speciality> getAllSpecialties() throws SQLException;
	
 }
