package com.intern.alexx.rest.controller;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intern.alexx.rest.AuthenticationEndpoint;
 


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans.xml")
public class AuthEndpointTest {


	private static Logger LOGGER = LoggerFactory.getLogger(AuthEndpointTest.class);

	@Autowired
	private AuthenticationEndpoint authEndpoint;
	
	@Test
	public void TestGetUsers_WhenSuccesful_ReturnUsers() throws SQLException{
		LOGGER.info( 
				authEndpoint.allUsers().toString());
		
	}
	
}
