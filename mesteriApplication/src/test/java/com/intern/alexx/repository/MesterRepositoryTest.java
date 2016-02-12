package com.intern.alexx.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

 
	@Test 
	public void testInsertMester_WhenSuccesfull_ThenReturnCreatedMester() {
		// when ->  given -> then
		Mester mester = createMester();
		mesterRepository.insert(mester);
		Mester dbMester = mesterRepository.getById(mester);
		assertNotNull(dbMester);
		assertEquals(mester.getLocation(), dbMester.getLocation());
	}

	@Test
	public void testUpdate_WhenSuccesfull_ThenUpdateMester() {
		Mester mester = createMester();
		Mester dbMester = mesterRepository.getById(mester);
		assertNotNull(dbMester);
		mesterRepository.update(mester);
		assertEquals(mester.getLocation(), dbMester.getLocation());
	}

	@Test
	public void testDelete_WhenSuccesfull_ThenDeleteMester() {
		Mester mester = createMester();
		Mester dbMester = mesterRepository.getById(mester);
		assertNotNull(dbMester);
		mesterRepository.update(mester);
		assertEquals(mester.getLocation(), dbMester.getLocation());
	}

	@Test
	public void testFindMester_WhenCalled_ThenMesterPageisReturned() {
		MesterSearchCriteria msc = createMSC();
		MyPage<Mester> page = mesterRepository.setupTheSearchMesterPage(msc);
		assertNotNull(page);
	}

	private Mester createMester() {
		Mester mester = new Mester();
		mester.setId(100);
		mester.setFirstName("Bill");
		mester.setLastName("Nye");
		mester.setLocation("Los Angeles");
		mester.setDescription("Bill Nye the Science Guy");
		return mester;
	}

	private MesterSearchCriteria createMSC() {
		MesterSearchCriteria msc = new MesterSearchCriteria();
		msc.setLocation("Los Angeles");
		msc.setPageNumber(1);
		msc.setPageSize(2);
		return msc;
	}
 
}
