package com.intern.alexx.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intern.alexx.model.AreaSearchCriteria;
import com.intern.alexx.model.Location;
import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.Speciality;
import com.intern.alexx.model.User;
import com.intern.alexx.repository.ContactRepository;
import com.intern.alexx.repository.LocationRepository;
import com.intern.alexx.repository.MesterRepository;
import com.intern.alexx.repository.SpecialityRepository;
import com.intern.alexx.repository.UserRepository;
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
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private LocationRepository locationRepo;
	

	@Transactional
	public void insertMester(Mester mester) {

		mesterRepository.insert(mester);
		LOGGER.info(mester.toString());
		mester.getContact().setIdMester(mester.getId());
		contactRepository.insert(mester.getContact());
		LOGGER.info(mester.getId());
		LOGGER.info(mester.getContact().toString());
		List<Speciality> specialities = mester.getSpeciality();
		if (specialities != null) {
			for (Speciality speciality : specialities) {
				LOGGER.info("---------" + speciality.toString());
				specialityService.verifySpeciality(speciality.getSpecialityName());
				speciality.setId(specRepo.getSpecialityIdByName(speciality.getSpecialityName()));
				mesterRepository.insertIntoMesterHasSpeciality(mester.getId(), speciality.getId());
				LOGGER.info("---------" + mester.getId() + "    +   " + speciality.getId() + "-------");
			}
		}
		LOGGER.info(mester.toString());
		Location location = new Location();
		location.setMesterId(mester.getId());
		locationRepo.insert(location);
	}

	@Transactional
	public void updateMester(Mester mester) {

		if (mester.getId() == null) {
			throw new IllegalArgumentException("mester ID must not be null");
		}

		mesterRepository.deleteFromMesterHasSpeciality(mester.getId());
		mesterRepository.update(mester);
		// mester.getContact().setId(contactRepository.getIDByIdMester(mester.getId()));
		mester.getContact().setIdMester(mester.getId());
		contactRepository.update(mester.getContact());
		List<Speciality> specialities = mester.getSpeciality();
		for (Speciality speciality : specialities) {
			specialityService.verifySpeciality(speciality.getSpecialityName());
			speciality.setId(specRepo.getSpecialityIdByName(speciality.getSpecialityName()));
			mesterRepository.insertIntoMesterHasSpeciality(mester.getId(), speciality.getId());
			LOGGER.info("---------" + mester.getId() + "    +   " + speciality.getId() + "-------");
		}
		User user=userRepo.getUserById(mester.getId());
		user.setEmail(mester.getContact().getEmail());
		userRepo.updateUserEmail(user);
	}

	@Transactional
	public void deleteMester(String mesterId) {
		if (mesterId == null) {
			throw new IllegalArgumentException("mester ID is null");
		}
		mesterRepository.deleteFromMesterHasSpeciality(mesterId);
		contactRepository.delete(mesterId);
		locationRepo.delete(mesterId);
		mesterRepository.delete(mesterId);
	}

	@Transactional
	public Mester getById(String idMester) {
		return mesterRepository.getById(idMester);
	}

	@Transactional
	public Mester getMesterById(String idMester) throws SQLException {
		LOGGER.info("--------1------");
		Mester mester = mesterRepository.getById(idMester);
		LOGGER.info("--------2------" + mester.toString());

		mester.setContact(contactRepository.getByIdMester(idMester));
		LOGGER.info("--------3------" + mester.toString());

		mester.setSpeciality(specialityService.getAllMesterSpeciality(idMester));
		LOGGER.info("--------4------" + mester.toString());
		return mester;
	}

	@Transactional
	public MyPage<Mester> searchMester(MesterSearchCriteria searchCriteria) throws SQLException {
		 MyPage<Mester> myPage = mesterRepository.prepareSearchForMester(searchCriteria);		 
		for (Mester mester : myPage.getContentPage()){
			mester.setContact(contactRepository.getByIdMester(mester.getId()));
		}
		return myPage;
	}

	@Transactional
	public List<Mester> searchMesterByArea(AreaSearchCriteria areaSearchCriteria) throws SQLException {
		List<Mester> listMesteri= mesterRepository.searchMesterByArea(areaSearchCriteria);
		for (Mester mester : listMesteri){
			mester.setContact(contactRepository.getByIdMester(mester.getId()));
		}
		return listMesteri;
	}

}
