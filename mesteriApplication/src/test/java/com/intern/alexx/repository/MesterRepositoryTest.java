package com.intern.alexx.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intern.alexx.model.Contact;
import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.Speciality;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/appContext.xml")
public class MesterRepositoryTest {

	@Autowired
	private MesterRepository mesterRepository;

	@Test
	public void testInsertMester_WhenSuccesfull_ThenReturnCreatedMester() {
		// when -> given -> then
		Mester mester = createMester();
		mesterRepository.insert(mester);
		Mester dbMester = mesterRepository.getById(mester);
		assertNotNull(dbMester);
		assertEquals(mester.getLocation(), dbMester.getLocation());
	}

	@Test
	public void testUpdate_WhenSuccesfull_ThenUpdateMester() {
		Mester mester = createMester();
		Mester dbMester = mesterRepository.getById(mester);
		assertNotNull(dbMester);
		mesterRepository.update(mester);
		assertEquals(mester.getLocation(), dbMester.getLocation());
	}

	@Test
	public void testDelete_WhenSuccesfull_ThenDeleteMester() {
		Mester mester = createMester();
		Mester dbMester = mesterRepository.getById(mester);
		assertNotNull(dbMester);
		mesterRepository.delete(mester);

	}

	@Test
	public void testFindMester_WhenCalled_ThenMesterPageisReturned() {
		MesterSearchCriteria msc = createMSC();
		MyPage<Mester> page = mesterRepository.setupTheSearchMesterPage(msc);
		assertNotNull(page);
	}

	@Test
	public void insertFullMester_WhenSuccesfull_ThenReturnFullMester() {
		Mester mester = createMester();
		Contact contact = createContract();
		Speciality speciality = createSpeciality();
		mesterRepository.insertFullMester(mester, contact, speciality);
		Mester dbMester = mesterRepository.getById(mester);
		assertNotNull(dbMester);
	}

	@Test
	public void testUpdateFullMester_WhenSuccesfull_ThenUpdateMester() {
		Mester mester = createMester();
		Contact contact = createContract();
		Speciality speciality = createSpeciality();
		Mester dbMester = mesterRepository.getById(mester);
		assertNotNull(dbMester);
		mesterRepository.updateFullMester(dbMester, contact, speciality);
		
	}
	
	
	@Test
	public void deleteFullMester_WhenSuccesfull_ThenReturnFullMester() {
		Mester mester = createMester();
		Mester dbMester = mesterRepository.getById(mester);
		assertNotNull(dbMester);
		mesterRepository.deleteFullMester(dbMester);
	}

	private Mester createMester() {
		Mester mester = new Mester();
		mester.setId(101);
		mester.setFirstName("Ion");
		mester.setLastName("Ionescu");
		mester.setLocation("Miami");
		mester.setDescription(" ");
		return mester;
	}

	private Contact createContract() {
		Contact contact = new Contact();
		// contact.setIdMester(666);
		contact.setEmail("mester@contact.com");
		contact.setTelNr("0479011333");
		contact.setSite("youtube");
		contact.setSocialPlatform("fb tw");
		return contact;
	}

	private Speciality createSpeciality() {
		Speciality speciality = new Speciality();
		speciality.setSpecialityName("stress2");
		return speciality;

	}

	private MesterSearchCriteria createMSC() {
		MesterSearchCriteria msc = new MesterSearchCriteria();
		msc.setLocation("Los Angeles");
		msc.setPageNumber(1);
		msc.setPageSize(2);
		return msc;
	}

}
