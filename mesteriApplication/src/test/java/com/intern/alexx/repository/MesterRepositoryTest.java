package com.intern.alexx.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intern.alexx.model.Contact;
import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.Speciality;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans.xml")
public class MesterRepositoryTest {

	private static final Logger logger = LoggerFactory.getLogger(GenerateSqlTest.class);
	
	@Autowired
	private MesterRepository mesterRepository;

	@Test
	public void testInsertMester_WhenSuccesfull_ThenReturnCreatedMester() {
		Mester mester = createMester();
		mesterRepository.insert(mester);
		assertNotNull(mester.getId());		
		Mester dbMester = mesterRepository.getById(mester.getId());
		assertNotNull(dbMester);
		assertEquals(mester.getLocation(), dbMester.getLocation());
	}

	@Test
	public void testUpdate_WhenSuccesfull_ThenUpdateMester() {
		Mester mester = createMester();
		mesterRepository.insert(mester);
		Mester dbMester = mesterRepository.getById(mester.getId());
		assertNotNull(dbMester);
		mesterRepository.update(mester);
		assertEquals(mester.getLocation(), dbMester.getLocation());
		mesterRepository.delete(mester.getId());
		 
	}

	@Test
	public void testDelete_WhenSuccesfull_ThenDeleteMester() {
		Mester mester = createMester();
		Mester dbMester = mesterRepository.getById(mester.getId());
		assertNotNull(dbMester);
		mesterRepository.delete(mester.getId());
	 
	}

	@Test
	public void testFindMester_WhenCalled_ThenMesterPageisReturned() throws SQLException {
		MesterSearchCriteria msc = createMSC();
		MyPage<Mester> page = mesterRepository.prepareSearchForMester(msc);
		logger.info(page.toString() );
		assertNotNull(page);
	}

	private Mester createMester() {
		Mester mester = new Mester();
		Contact contact = createContract();
		Speciality speciality = createSpeciality();
		mester.setId("66");
		mester.setFirstName("Ionel");
		mester.setLastName("Ionescu");
		mester.setLocation("Miami");
		mester.setDescription(" ");
		mester.setContact(contact);
		mester.setSpeciality(speciality);
		return mester;
	}

	private Contact createContract() {
		Contact contact = new Contact();
		contact.setId("66");
		contact.setIdMester("66");
		contact.setEmail("mester@contact.com");
		contact.setTelNr("0479011333");
		contact.setSite("youtube");
		contact.setSocialPlatform("fb tw");
		return contact;
	}

	private Speciality createSpeciality() {
		Speciality speciality = new Speciality();
		speciality.setSpecialityName("zidar");
		return speciality;

	}

	private MesterSearchCriteria createMSC() {
		MesterSearchCriteria msc = new MesterSearchCriteria();
		//msc.setLocation("Cluj");
		msc.setSpecialityName("instalator");
		msc.setPageNumber(3);
		msc.setPageSize(2);
		return msc;
	}

}
