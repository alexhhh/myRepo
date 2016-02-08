/**
 * 
 */
package com.intern.alexx.repository;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author malex
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/appContext.xml")
public class MySqlTest {

	
	private static final Logger logger = LoggerFactory.getLogger(MySqlTest.class);
	private Connection connection;
	
	@Autowired
	private DataSource dataSource;

	@BeforeClass
	public static void beforeAllTests() {

		logger.info("before all tests !");
	}

	@Before
	public void init() throws SQLException {

		logger.info("init.");
		connection = dataSource.getConnection();
	}

	@Test
	public void canConnectToDataBase() throws SQLException {

		assertNotNull(connection);

	}

	@After
	public void cleanUp() throws SQLException {

		if (connection != null) {
			connection.close();
		}
		
	}

}
