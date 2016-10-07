package com.ikon.alexx.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ikon.alexx.converters.SpecialityConverter;
import com.ikon.alexx.entity.Mester;
import com.ikon.alexx.model.SpecialityDTO;
import com.ikon.alexx.repository.MesterRepository;
import com.ikon.alexx.repository.SpecialityRepository;
import com.ikon.alexx.service.SpecialityService;

@Component
@Transactional
public class SpecialityServiceImp implements SpecialityService {

	@Autowired
	private SpecialityRepository specRepo;
	
	@Autowired
	private SpecialityConverter specConv;

	@Autowired
	private MesterRepository mesterRepo;

	public void insertSpeciality(SpecialityDTO specialityDTO) {
		specRepo.save(specConv.toEntity(specialityDTO));
	}

	public void updateSpeciality(SpecialityDTO specialityDTO) {
		specRepo.save(specConv.toEntity(specialityDTO));
	}

	public void deleteSpeciality(String idSpeciality) {
		specRepo.delete(idSpeciality);
	}

	public String getSpecialityIdByName(String specialityName) {
		return specRepo.findBySpecialityName(specialityName).getId();
	}

	public List<SpecialityDTO> getAllMesterSpeciality(String mesterId) throws SQLException {
		Mester m = mesterRepo.findOne(mesterId);
		return specConv.fromEntities(new ArrayList<>(m.getSpecialities()));
	}

	public List<SpecialityDTO> getAllSpeciality() throws SQLException {
		return specConv.fromEntities(specRepo.findAll());
	}

}
