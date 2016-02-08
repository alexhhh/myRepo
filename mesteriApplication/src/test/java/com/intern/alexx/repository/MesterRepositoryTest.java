package com.intern.alexx.repository;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/appContext.xml")
public class MesterRepositoryTest {

	@Autowired
	private MesterRepository mesterRepository;

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
	
	 
	
	@After
	public void searchAfterLocation(){
		
		MesterSearchCriteria msc =createMSC();
		MyPage page = mesterRepository.search(msc);
		assertNotNull(page);
		 
		
	}
	
	
	private Mester createMester() {

		Mester mester = new Mester();
		
		mester.setFirstName("Bogdan");
		mester.setLastName("Popescu");
		mester.setLocation("Tibet");
		return mester;
	} 
	
	private MesterSearchCriteria createMSC() {

		MesterSearchCriteria msc = new MesterSearchCriteria();
		 
		msc.setLocation("Tibet");
		msc.setPageNumber(1);
		msc.setPageSize(20);
		return msc;
	} 
	
}
