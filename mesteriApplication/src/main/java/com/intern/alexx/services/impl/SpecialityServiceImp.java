package com.intern.alexx.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intern.alexx.model.Speciality;
import com.intern.alexx.repository.SpecialityRepository;
import com.intern.alexx.services.SpecialityService;

@Component
public class SpecialityServiceImp implements SpecialityService {

	private static Logger log = LoggerFactory.getLogger(SpecialityServiceImp.class);

	@Autowired
	private SpecialityRepository specialityRepository;

	@Transactional
	public List<Speciality> getAllMesterSpeciality(String mesterId) throws SQLException {
		log.info("--Service-- Get all mester speciality.");
		return specialityRepository.getAllMesterSpecialities(mesterId);
	}

	@Transactional
	public List<Speciality> getAllSpeciality() throws SQLException {
		log.info("--Service-- Get all specialitys.");
		return specialityRepository.getAllSpecialties();
	}

	@Transactional
	public void insertSpeciality(Speciality speciality) {
		log.info("--Service-- Insert speciality.");
		specialityRepository.insert(speciality);
	}

	@Transactional
	public void updateSpeciality(Speciality speciality) {
		log.info("--Service-- Update speciality.");
		specialityRepository.update(speciality);
	}

	@Transactional
	public void deleteSpeciality(String idSpeciality) {
		log.info("--Service-- Delete speciality.");
		specialityRepository.delete(idSpeciality);
	}

	@Transactional
	public String getSpecialityIdByName(String specialityName) {
		log.info("--Service-- Delete speciality.");
		return specialityRepository.getSpecialityIdByName(specialityName);
	}

}
