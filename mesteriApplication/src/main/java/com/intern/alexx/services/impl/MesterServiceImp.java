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

	@Transactional
	public void insertMester(Mester mester) {
	 
		if (mester.getId() != null){
			throw new IllegalArgumentException("mester ID shoud be null");
		}
		mesterRepository.insert(mester);
		LOGGER.info(mester.toString());
		mester.getContact().setIdMester(mester.getId());
		contactRepository.insert(mester.getContact());
		LOGGER.info(mester.getId());
		LOGGER.info(mester.getContact().toString());
	 
	//	specialityService.verifySpeciality(mester.getSpeciality());
		//TODO verify if E spec , and add if not
//		if (specialityRepository.getSpecialityIdByName(mester.getSpeciality().getSpecialityName())  != null) {
//			mester.setSpeciality(specialityRepository.getByName(mester.getSpeciality().getSpecialityName()));
//		} else {
//			specialityRepository.insert(mester.getSpeciality());
//			LOGGER.info(mester.getSpeciality().toString());
//		}
//		LOGGER.info("---------"+ mester.getId() +"    +   " + mester.getSpeciality().getId()+"-------");
//		mesterRepository.insertIntoMesterHasSpeciality(mester.getId(), mester.getSpeciality().getId());
		LOGGER.info(mester.toString());
	}

	@Transactional
	public void updateMester(Mester mester) {
		
		if (mester.getId() == null){
			throw new IllegalArgumentException("mester ID must not be null");
		}
		
		mesterRepository.deleteFromMesterHasSpeciality(mester.getId());
		mesterRepository.update(mester);
		mester.getContact().setIdMester(mester.getId());
		contactRepository.update( mester.getContact());
//		if (specialityRepository.getSpecialityIdByName(mester.getSpeciality().getSpecialityName()) != null) {
//			mester.setSpeciality(specialityRepository.getByName(mester.getSpeciality().getSpecialityName()));
//		} else {
//			specialityRepository.insert(mester.getSpeciality());
//			LOGGER.info("----------" +mester.getSpeciality().toString());
//		}
//		LOGGER.info("---------"+ mester.getId() +"    +   " + mester.getSpeciality().getId()+"-------");
//		mesterRepository.insertIntoMesterHasSpeciality(mester.getId(), mester.getSpeciality().getId());
	}

	@Transactional
	public void deleteMester(String mesterId) {
		if (mesterId == null){
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

	public MyPage<Mester> searchMester(MesterSearchCriteria searchCriteria) throws SQLException {
		return mesterRepository.prepareSearchForMester(searchCriteria);
	}

}
