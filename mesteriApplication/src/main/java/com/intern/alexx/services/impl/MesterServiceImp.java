package com.intern.alexx.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.Contact;
import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.Speciality;
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

	public MyPage<Mester> searchMesterPage(MesterSearchCriteria searchCriteria) {	 
		return mesterRepository.setupTheSearchMesterPage(searchCriteria);
	}

	public void insertMester(Mester mester, Contact contact, Speciality speciality) {
		mesterRepository.insert(mester);
		contactRepository.insert(contact);
		specialityRepository.insert(speciality);		
	}

	public void updateMester(Mester mester) {
		mesterRepository.update(mester);

	}

	public void deleteMester(Mester mester) {
		mesterRepository.delete(mester);

	}

	public Mester getById(Mester mester) {
		mesterRepository.getById(mester);
		return mester;
	}

	public void insertFullMesterDetalis() {
		 
		
	}

}
