/**
 * 
 */
package com.intern.alexx.repository;

 
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
	
	Speciality getByName(String specialityName);
	
	List<Speciality> getAllSpecialties();
	
 }
