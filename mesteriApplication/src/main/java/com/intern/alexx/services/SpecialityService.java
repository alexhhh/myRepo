package com.intern.alexx.services;

import java.sql.SQLException;
import java.util.List;

import com.intern.alexx.model.Speciality;

public interface SpecialityService {

	
	List<Speciality> getAllMesterSpeciality(String mesterId) throws SQLException;
	
	List<Speciality> getAllSpeciality() throws SQLException;
 
	void insertSpeciality(Speciality speciality);

	void updateSpeciality(Speciality speciality);

	void deleteSpeciality(String idSpeciality);
	
	String getSpecialityIdByName(String specialityName);
}
