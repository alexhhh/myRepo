package com.intern.alexx.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.repository.impl.GenerateSql;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/appContext.xml")
public class GenerateSqlTest {

	@Autowired
	GenerateSql genSql;

	private static final Logger logger = LoggerFactory.getLogger(GenerateSqlTest.class);
	
	@Test
	public void withoutConditions() {
				
		String query;
		MesterSearchCriteria msc = new MesterSearchCriteria();
		query = genSql.createString(msc );
		assertNotNull(query);
		logger.info("query-ul pt nici un parametru : " );
		logger.info(query.toString());
	}
	
	@Test
	public void firstLevelOfQueryComplexity() {
		
		String query;
		MesterSearchCriteria msc = new MesterSearchCriteria( );
		msc.setFirstName("Bogdan");
		msc.setLastName("Popescu");
		msc.setLocation("Tibet");	 
		query = genSql.createString(msc );
		assertNotNull(query);
		logger.info("query-ul pt LvL 1: " );
		logger.info(query.toString());
	}
	@Test
	public void secondLevelOfQueryComplexity() {
		
		String query;
		MesterSearchCriteria msc = new MesterSearchCriteria( );
		msc.setFirstName("Bogdan");
		msc.setLastName("Popescu");
		msc.setLocation("Tibet");
		msc.setSpecialityName("prostu satului");	 
		query = genSql.createString(msc );
		assertNotNull(query);
		logger.info("query-ul pt LvL 2: " );
		logger.info(query.toString());
	}
 
	@Test
	public void  thirdLevelOfQueryComplexity() {
		
		String query;
		MesterSearchCriteria msc = new MesterSearchCriteria( );
		msc.setFirstName("Bogdan");
		msc.setLastName("Popescu");
		msc.setLocation("Tibet");
		msc.setSpecialityName("prost");
		msc.setEmail("tataPuiu@echipaNationala.ro");
		msc.setPhoneNumber("0749011685");
		msc.setRating(10);
		msc.setPrice("fara numar");
		query = genSql.createString(msc );
		assertNotNull(query);
		logger.info("query-ul pt LvL 3: " );
		logger.info(query.toString());
	}
}
