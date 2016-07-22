package com.ikon.alexx.service;


import java.sql.SQLException;
import java.util.List;

import com.ikon.alexx.model.SpecialityDTO;

public interface SpecialityService {

	
	List<SpecialityDTO> getAllMesterSpeciality(String mesterId) throws SQLException;
	
	List<SpecialityDTO> getAllSpeciality() throws SQLException;
 
	void insertSpeciality(SpecialityDTO speciality);

	void updateSpeciality(SpecialityDTO speciality);

	void deleteSpeciality(String idSpeciality);
	
	String getSpecialityIdByName(String specialityName);
}
