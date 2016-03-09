package com.intern.alexx.service;

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
import com.intern.alexx.services.SpecialityService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans.xml")
public class SpecialityServiceTest {

	private static Logger LOGGER = LoggerFactory.getLogger(SpecialityServiceTest.class);
	
	@Autowired
	private SpecialityService specService;
	
	@Test
	public void testGetMesterSpec_WhenSuccesful_thenReturnSpecs() throws SQLException{	
		List<Speciality> spec =	specService.getAllMesterSpeciality("10");
		LOGGER.info(spec.toString());
	}
}
