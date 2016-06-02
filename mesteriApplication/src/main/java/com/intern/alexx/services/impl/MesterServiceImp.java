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
import com.intern.alexx.repository.MesterRepository;
import com.intern.alexx.services.LocationService;
import com.intern.alexx.services.MesterService;
import com.intern.alexx.services.SpecialityService;
import com.intern.alexx.services.UserService;

@Component

public class MesterServiceImp implements MesterService {

	private static Logger LOGGER = LoggerFactory.getLogger(MesterService.class);

	@Autowired
	private MesterRepository mesterRepository;
	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private SpecialityService specialityService;

	@Autowired
	private LocationService locationService;

	@Transactional
	public void insertMester(Mester mester) {
		insert(mester);
		mester.getContact().setIdMester(mester.getId());
		contactRepository.insert(mester.getContact());
		List<Speciality> specialities = mester.getSpeciality();
		if (specialities != null) {
			for (Speciality speciality : specialities) {
				mesterRepository.insertIntoMesterHasSpeciality(mester.getId(), speciality.getId());
			}
		}
		LOGGER.info("-- Service -- insert mester---> " + mester.toString());

		Location location = new Location();
		location.setMesterId(mester.getId());
		locationService.insert(location);
	}

	@Transactional
	public void updateMester(Mester mester) {
		if (mester.getId() == null) {
			throw new IllegalArgumentException("mester ID must not be null");
		}

		mesterRepository.deleteFromMesterHasSpeciality(mester.getId());
		update(mester);
		mester.getContact().setIdMester(mester.getId());
		contactRepository.update(mester.getContact());
		List<Speciality> specialities = mester.getSpeciality();
		for (Speciality speciality : specialities) {
			mesterRepository.insertIntoMesterHasSpeciality(mester.getId(), speciality.getId());
		}
		LOGGER.info("-- Service -- update mester---> " + mester.toString());

		User user = userService.getUserById(mester.getId());
		user.setEmail(mester.getContact().getEmail());
		userService.updateUserEmail(user);
	}

	@Transactional
	public void deleteMester(String mesterId) {
		if (mesterId == null) {
			throw new IllegalArgumentException("mester ID is null");
		}
		LOGGER.info("--Service-- Delete mester with Id: " + mesterId);
		mesterRepository.deleteFromMesterHasSpeciality(mesterId);
		contactRepository.delete(mesterId);
		locationService.delete(mesterId);
		delete(mesterId);
	}

	@Transactional
	public Mester getMesterById(String idMester) throws SQLException {
		Mester mester = mesterRepository.getById(idMester);
		mester.setContact(contactRepository.getByIdMester(idMester));
		mester.setSpeciality(specialityService.getAllMesterSpeciality(idMester));
		LOGGER.info("--Service-- Get mester by Id" + mester.toString());
		return mester;
	}

	@Transactional
	public MyPage<Mester> searchMester(MesterSearchCriteria searchCriteria) throws SQLException {
		MyPage<Mester> myPage = mesterRepository.prepareSearchForMester(searchCriteria);
		for (Mester mester : myPage.getContentPage()) {
			mester.setContact(contactRepository.getByIdMester(mester.getId()));
		}
		return myPage;
	}

	@Transactional
	public List<Mester> searchMesterByArea(AreaSearchCriteria areaSearchCriteria) throws SQLException {
		List<Mester> listMesteri = mesterRepository.searchMesterByArea(areaSearchCriteria);
		for (Mester mester : listMesteri) {
			mester.setContact(contactRepository.getByIdMester(mester.getId()));
		}
		return listMesteri;
	}

	@Transactional
	public Mester getById(String idMester) {
		return mesterRepository.getById(idMester);
	}

	@Transactional
	public void insert(Mester mester) {
		LOGGER.info("--Service-- Insert mester");
		mesterRepository.insert(mester);
	}

	@Transactional
	public void update(Mester mester) {
		LOGGER.info("--Service-- Update mester");
		mesterRepository.update(mester);
	}

	@Transactional
	public void delete(String mesterId) {
		LOGGER.info("--Service-- Delete mester");
		mesterRepository.delete(mesterId);
	}

	@Transactional
	public void insertMesterSpeciality(String specialtyId, String mesterId) {
		LOGGER.info("--Service-- Insert mester speciality.");
		mesterRepository.insertIntoMesterHasSpeciality(mesterId, specialtyId);
	}

	@Transactional
	public void deleteOneMesterSpeciality(String specialtyId, String mesterId) {
		LOGGER.info("--Service-- Delete mester speciality.");
		mesterRepository.deleteOneFromMesterHasSpeciality(mesterId, specialtyId);
	}

	@Override
	public void updateAvg(Mester mester) {
		mesterRepository.updateAvg(mester);		
	}

}
