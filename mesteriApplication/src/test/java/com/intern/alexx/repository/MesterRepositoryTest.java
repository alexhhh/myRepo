package com.intern.alexx.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.Speciality;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/appContext.xml")
public class MesterRepositoryTest {

	@Autowired
	private MesterRepository mesterRepository;


	private static final Logger logger = LoggerFactory.getLogger(GenerateSqlTest.class);
	
//	@Test
//	public void canInsertMesterIntoDB() {
//
//		Mester mester = createMester();
//		mesterRepository.insert(mester);
//		
//		Mester dbMester = mesterRepository.getById(mester);
//		assertNotNull(dbMester);
//		assertEquals(mester.getLocation(), dbMester.getLocation());
//
//	}
	
//	@Test
//	public void canDeleteMesterFromDB(){
//		Mester mester = new Mester();
//		Mester dbMester = mesterRepository.getById(mester);
//		assertNotNull(dbMester);
//		mesterRepository.delete(mester);
//	 
//	}
//	
//	
//	@Test
//	public void canUpdateMesterFromDb(){
//		// review
//		Mester mester = new Mester();
//		Mester dbMester = mesterRepository.getById(mester);
//		assertNotNull(dbMester);
//		mesterRepository.update(mester);
//		assertEquals(mester.getLocation(), dbMester.getLocation());
//	}
	
	 
	
	@Test
	public void searchAfterLocation(){
		logger.info("Ajunge pana aici");
		MesterSearchCriteria msc =createMSC();
		MyPage page = mesterRepository.search(msc);
		
		assertNotNull(page);
		logger.info(page.getPageSize().toString());
		 logger.info("Page-ul returnat e: " );
		 logger.info(page.toString());
	}
	
	
	private Mester createMester() {

		Mester mester = new Mester();	 
		mester.setFirstName("Michio");
		mester.setLastName("Kaku");
		mester.setLocation("New York");		 
		return mester;
	} 
	
	private MesterSearchCriteria createMSC() {

		MesterSearchCriteria msc = new MesterSearchCriteria();
		 
		msc.setLocation("Tibet");
		msc.setPageNumber(3);
		msc.setPageSize(2);
		return msc;
	} 
	
}
