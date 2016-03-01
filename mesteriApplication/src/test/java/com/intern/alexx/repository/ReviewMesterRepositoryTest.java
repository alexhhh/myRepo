package com.intern.alexx.repository;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester;
import com.intern.alexx.model.ReviewMester.Price;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans.xml")
public class ReviewMesterRepositoryTest {

	@Autowired
	private ReviewMesterRepository reviewMesterRepository;

 
	@Test
	public void testInsertReview_WhenSuccesfull_ThenReturnCreatedReview() {
		ReviewMester review = createReviewMester();
		reviewMesterRepository.insert(review);
		ReviewMester dbReview = reviewMesterRepository.getById(review.getId());	
		assertNotNull(dbReview);
		
		assertEquals(review.getIdMester(), dbReview.getIdMester());

	}

	@Test
	public void testUpdate_WhenSuccesfull_ThenUpdateReview() {
		ReviewMester review = createReviewMester();
		reviewMesterRepository.insert(review);
		ReviewMester dbReview = reviewMesterRepository.getById(review.getId());
		assertNotNull(dbReview);
		reviewMesterRepository.update(review);
		assertEquals(review.getIdMester() , dbReview.getIdMester());
		reviewMesterRepository.delete(review.getId());
	 
	}

	@Test
	public void testDelete_WhenSuccesfull_ThenDeleteReview() {
		ReviewMester review = createReviewMester();
		ReviewMester dbReview = reviewMesterRepository.getById(review.getId());
		assertNotNull(dbReview);
		reviewMesterRepository.delete(review.getId());
	 
	}

	@Test
	public void testGetAllReviews_WhenCalled_ThenReturnPageWithReviews() throws SQLException {
		MesterSearchCriteria msc = createMSC();
		MyPage<ReviewMester> page = reviewMesterRepository.getAllReviewsPage(msc);
		assertNotNull(page);
  
	}
	
	@Test
	public void testGetMesterReviews_WhenCalled_ThenReturnPageWithReviews() throws SQLException {
		MesterSearchCriteria msc = createMSC();
		MyPage<ReviewMester> page = reviewMesterRepository.getAllReviewForMester("10",msc);
		assertNotNull(page);
	}

	private ReviewMester createReviewMester() {
		ReviewMester reviewMester = new ReviewMester();
		reviewMester.setId("55");
		reviewMester.setIdMester("10");
		reviewMester.setIdClient("5");
		reviewMester.setPrice(Price.LOW);
		reviewMester.setRating(5);
		reviewMester.setFeedback("Awesome Update");
		return reviewMester;
	}

	private MesterSearchCriteria createMSC() {
		MesterSearchCriteria msc = new MesterSearchCriteria();
		msc.setPageNumber(3);
		msc.setPageSize(2);
		return msc;
	}

}
