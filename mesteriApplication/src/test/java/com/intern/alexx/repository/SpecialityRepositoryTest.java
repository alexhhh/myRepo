package com.intern.alexx.repository;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intern.alexx.model.Speciality;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans.xml")
public class SpecialityRepositoryTest {

	private static Logger LOGGER = LoggerFactory.getLogger(SpecialityRepositoryTest.class);
	@Autowired
	SpecialityRepository specRepo;

	@Test
	public void testGetSpec_WhenSuccesful_ThenReturnSpec() {
		Speciality spec = new Speciality();
		spec = specRepo.getByName("metalhead");
		System.out.println(spec.toString());
	}

	@Test
	public void testGetMesterSpec_WhenSuccesful_thenReturnSpecs() throws SQLException {
		List<Speciality> spec = specRepo.getAllMesterSpecialities("10");
		LOGGER.info(spec.toString());
	}
}
