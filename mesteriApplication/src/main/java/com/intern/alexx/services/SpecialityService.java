package com.intern.alexx.services;

import java.util.List;

import com.intern.alexx.model.Speciality;

public interface SpecialityService {

	void insertMesterSpeciality(String specialityName, String mesterId);

	void deleteOneMesterSpeciality(String specialityName, String mesterId);

	List<String> getAllMesterSpeciality(String mesterId);
	
	List<String> getAllSpeciality();

	public Speciality verifySpeciality(String specialityName);
}
