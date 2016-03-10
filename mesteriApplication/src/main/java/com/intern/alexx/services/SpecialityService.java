package com.intern.alexx.services;

import java.sql.SQLException;
import java.util.List;

import com.intern.alexx.model.Speciality;

public interface SpecialityService {

	void insertMesterSpeciality(String specialityName, String mesterId);

	void deleteOneMesterSpeciality(String specialityName, String mesterId);

	List<Speciality> getAllMesterSpeciality(String mesterId) throws SQLException;
	
	List<Speciality> getAllSpeciality() throws SQLException;

	public Speciality verifySpeciality(String specialityName);
	
	void insertSpeciality(Speciality speciality);

	void updateSpeciality(Speciality speciality);

	void deleteSpeciality(String idSpeciality);
}
