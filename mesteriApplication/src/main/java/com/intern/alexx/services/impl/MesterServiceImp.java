package com.intern.alexx.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.Speciality;
import com.intern.alexx.repository.ContactRepository;
import com.intern.alexx.repository.MesterRepository;
import com.intern.alexx.repository.SpecialityRepository;
import com.intern.alexx.services.MesterService;
import com.intern.alexx.services.SpecialityService;

@Component

public class MesterServiceImp implements MesterService {

	private static Logger LOGGER = LoggerFactory.getLogger(MesterService.class);

	@Autowired
	private MesterRepository mesterRepository;
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private SpecialityService specialityService;
	@Autowired
	private SpecialityRepository specRepo;

	@Transactional
	public void insertMester(Mester mester) {

		if (mester.getId() != null) {
			throw new IllegalArgumentException("mester ID shoud be null");
		}
		mesterRepository.insert(mester);
		LOGGER.info(mester.toString());
		mester.getContact().setIdMester(mester.getId());
		contactRepository.insert(mester.getContact());
		LOGGER.info(mester.getId());
		LOGGER.info(mester.getContact().toString());
		List<Speciality> specialities = mester.getSpeciality();
		for (Speciality speciality : specialities) {
			LOGGER.info("---------"+ speciality.toString());
			specialityService.verifySpeciality(speciality.getSpecialityName());
			speciality.setId(specRepo.getSpecialityIdByName(speciality.getSpecialityName()));
			mesterRepository.insertIntoMesterHasSpeciality(mester.getId(), speciality.getId());
			LOGGER.info("---------" + mester.getId() + "    +   " + speciality.getId() + "-------");
		}
		LOGGER.info(mester.toString());
	}

	@Transactional
	public void updateMester(Mester mester) {

		if (mester.getId() == null) {
			throw new IllegalArgumentException("mester ID must not be null");
		}

		mesterRepository.deleteFromMesterHasSpeciality(mester.getId());
		mesterRepository.update(mester);
		mester.getContact().setId(contactRepository.getIDByIdMester(mester.getId()));
		mester.getContact().setIdMester(mester.getId());
		contactRepository.update(mester.getContact());
		List<Speciality> specialities = mester.getSpeciality();
		for (Speciality speciality : specialities) {
			specialityService.verifySpeciality(speciality.getSpecialityName());
			speciality.setId(specRepo.getSpecialityIdByName(speciality.getSpecialityName()));
			mesterRepository.insertIntoMesterHasSpeciality(mester.getId(), speciality.getId());
			LOGGER.info("---------" + mester.getId() + "    +   " + speciality.getId() + "-------");
		}
	}

	@Transactional
	public void deleteMester(String mesterId) {
		if (mesterId == null) {
			throw new IllegalArgumentException("mester ID is null");
		}
		mesterRepository.deleteFromMesterHasSpeciality(mesterId);
		contactRepository.delete(mesterId);
		mesterRepository.delete(mesterId);

	}
 
	@Transactional
	public Mester getById(String idMester) {
		Mester mester = mesterRepository.getById(idMester);
		return mester;
	}
	
	@Transactional
	public Mester getMesterById(String idMester) throws SQLException {
		LOGGER.info("--------1------");
		Mester mester = mesterRepository.getById(idMester);
		LOGGER.info("--------2------" + mester.toString());

		mester.setContact(contactRepository.getByIdMester(idMester)) ;
		LOGGER.info("--------3------"+  mester.toString());

	
		mester.setSpeciality(specialityService.getAllMesterSpeciality(idMester));
		LOGGER.info("--------4------"+  mester.toString());
		return mester;
	}

	public MyPage<Mester> searchMester(MesterSearchCriteria searchCriteria) throws SQLException {
		return mesterRepository.prepareSearchForMester(searchCriteria);
	}

}
