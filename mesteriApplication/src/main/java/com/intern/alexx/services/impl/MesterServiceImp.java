package com.intern.alexx.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.repository.MesterRepository;
import com.intern.alexx.services.MesterService;

@Component
public class MesterServiceImp implements MesterService {

	@Autowired
	private MesterRepository mesterRepository;

	public void insertMester(Mester mester) {
		mesterRepository.insertFullMester(mester, mester.getContact(), mester.getSpeciality());
	}

	public void updateMester(Mester mester) {
		mesterRepository.updateFullMester(mester, mester.getContact(), mester.getSpeciality());
	}

	public void deleteMester(Mester mester) {
		mesterRepository.deleteFullMester(mester);
	}

	public Mester getById(Mester mester) {
		mesterRepository.getById(mester);
		return mester;
	}
	
	public MyPage<Mester> searchMesterPage(MesterSearchCriteria searchCriteria) {
		return mesterRepository.setupTheSearchMesterPage(searchCriteria);
	}

}
