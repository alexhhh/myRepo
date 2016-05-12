package com.intern.alexx.service;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intern.alexx.model.Contact;
import com.intern.alexx.model.Mester;
import com.intern.alexx.model.Speciality;
import com.intern.alexx.repository.GenerateSqlTest;
import com.intern.alexx.repository.impl.GUIDGenerator;
import com.intern.alexx.services.MesterService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans.xml")
public class MesterServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(GenerateSqlTest.class);

	@Autowired
	private MesterService mesterService;

	@Test
	public void testInsertMester_WhenSuccesfull_ThenReturnCreatedMester() {
		Mester mester = createMester();
		mesterService.insertMester(mester);
		Mester newMester = mesterService.getById(mester.getId());
		logger.info(newMester.toString());
		mesterService.deleteMester(mester.getId()); 
	}

	@Test
	public void testUpdate_WhenSuccesfull_ThenUpdateMester() {
		Mester mester = createMester();
		mesterService.insertMester(mester);
		Mester dbMester = mesterService.getById(mester.getId());
		assertNotNull(dbMester);
		mesterService.updateMester(mester);
		assertEquals(mester.getLocation(), dbMester.getLocation());
		System.out.println("--***--" + mester.getId());
		mesterService.deleteMester(mester.getId());

	}

	@Test
	public void testDelete_WhenSuccesfull_ThenDeleteMester() {
		Mester mester = createMester();
		mester.setId(GUIDGenerator.generatedID());
		Mester dbMester = mesterService.getById(mester.getId());
		assertNotNull(dbMester);
		mesterService.deleteMester(mester.getId());

	}
	
	
	@Test
	public void testGetMester_WhenSuccesfull_ThenReturnMester() throws SQLException {
	 
		
		Mester dbMester = mesterService.getMesterById("c1ac53bc-a690-4328-a8a9-cec6c8c6f7b0");
		assertNotNull(dbMester);
		System.out.println("----------"  +dbMester.toString());
		 

	}
	

	private Mester createMester() {
		Mester mester = new Mester();
		Contact contact = createContract();
		List<Speciality> speciality = createSpeciality();
		mester.setId("66");
		mester.setMesterUserId("3");
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

		contact.setEmail("mester@contact.com");
		contact.setTelNr("0479011333");
		contact.setSite("youtube");
		contact.setSocialPlatform("fb tw");
		return contact;
	}

	private List<Speciality> createSpeciality() {
		List<Speciality> spec = new ArrayList<Speciality>();
		Speciality speciality = new Speciality();
		speciality.setSpecialityName("metalhead");
		speciality.setId("e527817a-f5f8-478a-85dc-a6b942d279d3");
		spec.add(speciality);
		return spec;

	}

}
