package com.intern.alexx.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intern.alexx.model.Speciality;
import com.intern.alexx.repository.MesterRepository;
import com.intern.alexx.repository.SpecialityRepository;
import com.intern.alexx.services.SpecialityService;

@Component
public class SpecialityServiceImp implements SpecialityService {

	@Autowired
	private SpecialityRepository specialityRepository;
	@Autowired
	private MesterRepository mesterRepository;

	@Override
	@Transactional
	public void insertMesterSpeciality(String specialityName, String mesterId) {
		Speciality speciality = verifySpeciality(specialityName);
		mesterRepository.insertIntoMesterHasSpeciality(mesterId, speciality.getId());
	}

	@Override
	@Transactional
	public void deleteOneMesterSpeciality(String specialityName, String mesterId) {
		mesterRepository.deleteOneFromMesterHasSpeciality(mesterId,
				specialityRepository.getSpecialityIdByName(specialityName));
	}

	@Transactional
	public List<Speciality> getAllMesterSpeciality(String mesterId) throws SQLException {
		return specialityRepository.getAllMesterSpecialities(mesterId);
	}

	@Transactional
	public List<Speciality> getAllSpeciality() throws SQLException {
		return specialityRepository.getAllSpecialties();
	}

	@Transactional
	public Speciality verifySpeciality(String specialityName) {

		if (specialityRepository.getSpecialityIdByName(specialityName) == null) {
			throw new IllegalArgumentException("verifyu ii prost !");
		}
		return specialityRepository.getByName(specialityName);

	}

	@Transactional
	public void insertSpeciality(Speciality speciality) {
		 specialityRepository.insert(speciality);
	}

	@Transactional
	public void updateSpeciality(Speciality speciality) {
		specialityRepository.update(speciality);
		
	}

	@Transactional
	public void deleteSpeciality(String idSpeciality) {
		specialityRepository.delete(idSpeciality);
	}

}
