package com.intern.alexx.services.impl;

import java.sql.SQLException;

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

@Component

public class MesterServiceImp implements MesterService {

	@Autowired
	private MesterRepository mesterRepository;
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private SpecialityRepository specialityRepository;

	@Transactional
	public void insertMester(Mester mester) {
	 
		mesterRepository.insert(mester);
		System.out.println(mester.toString());
		contactRepository.insert(mester.getContact());
		System.out.println(mester.getContact().toString());
		if (specialityRepository.getByName(mester.getSpeciality().getSpecialityName()) != null) {
			mester.setSpeciality(specialityRepository.getByName(mester.getSpeciality().getSpecialityName()));
		} else {
			specialityRepository.insert(mester.getSpeciality());
			System.out.println(mester.getSpeciality().toString());
		}
		mesterRepository.insertIntoMesterHasSpeciality(mester.getId(), mester.getSpeciality().getId());
		System.out.println(mester.toString());
	}

	@Transactional
	public void updateMester(Mester mester) {
		mesterRepository.deleteFromMesterHasSpeciality(mester.getId());
		mesterRepository.update(mester);
		contactRepository.update(mester.getContact());
		if (specialityRepository.getByName(mester.getSpeciality().getSpecialityName()) != null) {
			mester.setSpeciality(specialityRepository.getByName(mester.getSpeciality().getSpecialityName()));
		} else {
			specialityRepository.insert(mester.getSpeciality());
			System.out.println(mester.getSpeciality().toString());
		}
		mesterRepository.insertIntoMesterHasSpeciality(mester.getId(), mester.getSpeciality().getId());
	}

	@Transactional
	public void deleteMester(String mesterId) {
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
