package com.intern.alexx.repository;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester; 

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
		assertEquals(review.getIdMester(),dbReview.getIdMester());
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
		MyPage<ReviewMester> page = reviewMesterRepository.getAllReviewsPage(4,1);
		assertNotNull(page);
  
	}
	
	@Test
	public void testGetMesterReviews_WhenCalled_ThenReturnPageWithReviews() throws SQLException { 
		MyPage<ReviewMester> page = reviewMesterRepository.getAllReviewForMester("10",4,1);
		assertNotNull(page);
	}

	private ReviewMester createReviewMester() {
		ReviewMester reviewMester = new ReviewMester();		 
		reviewMester.setIdMester("3");
		reviewMester.setIdClient("2");
		reviewMester.setTitle("test");
		reviewMester.setPrice(3);
		reviewMester.setRating(5);
		reviewMester.setFeedback("Awesome Update");
		return reviewMester;
	}
 
}
