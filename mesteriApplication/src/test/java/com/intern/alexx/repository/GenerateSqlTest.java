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
				
		String query, countQuery;
		MesterSearchCriteria msc = new MesterSearchCriteria();	
		msc.setPageNumber(2);;	
		msc.setPageSize(20); 
		query = genSql.createQueryForElements(msc );
		countQuery= genSql.createQueryForCountElements(msc);
		assertNotNull(query);
		assertNotNull(countQuery);
		logger.info("query-ul pt LvL 3: " );
		logger.info(query.toString());
		logger.info("count Query-ul pt LvL 3: " );
		logger.info(countQuery.toString());
	}
	
	@Test
	public void firstLevelOfQueryComplexity() {
		
		String query, countQuery;
		MesterSearchCriteria msc = new MesterSearchCriteria( );
		msc.setFirstName("Bogdan");
		msc.setLastName("Popescu");
		msc.setLocation("Tibet");	 
		msc.setPageNumber(2);;	
		msc.setPageSize(20); 	
		query = genSql.createQueryForElements(msc );
		countQuery= genSql.createQueryForCountElements(msc);
		assertNotNull(query);
		assertNotNull(countQuery);
		logger.info("query-ul pt LvL 3: " );
		logger.info(query.toString());
		logger.info("count Query-ul pt LvL 3: " );
		logger.info(countQuery.toString());
	}
	@Test
	public void secondLevelOfQueryComplexity() {
		
		String query, countQuery;
		MesterSearchCriteria msc = new MesterSearchCriteria( );
		msc.setFirstName("Bogdan");
		msc.setLastName("Popescu");
		msc.setLocation("Tibet");
		msc.setSpecialityName("prostu satului");	 
		query = genSql.createQueryForElements(msc );
		countQuery= genSql.createQueryForCountElements(msc);
		assertNotNull(query);
		assertNotNull(countQuery);
		logger.info("query-ul pt LvL 3: " );
		logger.info(query.toString());
		logger.info("count Query-ul pt LvL 3: " );
		logger.info(countQuery.toString());
	}
 
	@Test
	public void  thirdLevelOfQueryComplexity() {
		
		String query, countQuery;
		MesterSearchCriteria msc = new MesterSearchCriteria( );
		msc.setFirstName("Bogdan");
		msc.setLastName("Popescu");
		msc.setLocation("Tibet");
		msc.setSpecialityName("prost");
		msc.setEmail("tataPuiu@echipaNationala.ro");
		msc.setPhoneNumber("0749011685");
		msc.setRating(10);
		msc.setPrice("fara numar");
		msc.setPageNumber(2);	
		msc.setPageSize(20); 
		query = genSql.createQueryForElements(msc );
		countQuery= genSql.createQueryForCountElements(msc);
		assertNotNull(query);
		assertNotNull(countQuery);
		logger.info("query-ul pt LvL 3: " );
		logger.info(query.toString());
		logger.info("count Query-ul pt LvL 3: " );
		logger.info(countQuery.toString());
	}
}
