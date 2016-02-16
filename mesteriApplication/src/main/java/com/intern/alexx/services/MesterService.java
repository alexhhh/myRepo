package com.intern.alexx.services;


import com.intern.alexx.model.Contact;
import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.Speciality;

/**
 * @author malex
 *
 */
public interface MesterService {

	void insertMester(Mester mester, Contact contact, Speciality speciality);

	void updateMester(Mester mester);

	void deleteMester(Mester mester);
	
	public Mester getById(Mester mester);

	MyPage<Mester> searchMesterPage(MesterSearchCriteria searchCriteria);
	
	void insertFullMesterDetalis();

}
