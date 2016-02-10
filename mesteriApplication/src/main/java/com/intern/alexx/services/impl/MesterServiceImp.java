/**
 * 
 */
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

	public MyPage<Mester> searchMester(MesterSearchCriteria searchCriteria) {
		MyPage<Mester> myPage = new MyPage<Mester>();
		myPage = mesterRepository.search(searchCriteria);
		return myPage;
	}

	public void insertMester(Mester mester) {
		mesterRepository.insert(mester);
	}

	public void updateMester(Mester mester) {
		mesterRepository.update(mester);

	}

	public void deleteMester(Mester mester) {
		mesterRepository.delete(mester);

	}

}
