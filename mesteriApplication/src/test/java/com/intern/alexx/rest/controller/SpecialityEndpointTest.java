package com.intern.alexx.rest.controller;

import java.sql.SQLException;
 

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intern.alexx.rest.SpecialityEndpoint;

 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans.xml")
public class SpecialityEndpointTest {

	private static Logger LOGGER = LoggerFactory.getLogger(SpecialityEndpointTest.class);

	@Autowired
	private SpecialityEndpoint specEndpoint;
	
	
	@Test
	public void testGetMesterSpec_WhenSuccesful_ReturnMesterSpecs() throws SQLException{
		LOGGER.info(specEndpoint.getMesterSpecialities("10").getEntity().toString());	
	}
	

	@Test
	public void testGetAllSpecs_WhenSuccesful_ReturnSpecs() throws SQLException{
		LOGGER.info(specEndpoint.getSpecialities().toString());	
	}
}
