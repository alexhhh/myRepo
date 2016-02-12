package com.intern.alexx.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/appContext.xml")
public class ReviewMesterRepositoryTest {

	@Autowired
	private ReviewMesterRepository reviewMesterRepository;

 
	@Test
	public void testInsertReview_WhenSuccesfull_ThenReturnCreatedReview() {
		ReviewMester review = createReviewMester();
		reviewMesterRepository.insert(review);
		ReviewMester dbReview = reviewMesterRepository.getById(review);
		assertNotNull(dbReview);
		assertEquals(review.getIdMester(), dbReview.getIdMester());

	}

	@Test
	public void testUpdate_WhenSuccesfull_ThenUpdateReview() {
		ReviewMester review = createReviewMester();
		ReviewMester dbReview = reviewMesterRepository.getById(review);
		assertNotNull(dbReview);
		reviewMesterRepository.update(review);
		assertEquals(review.getIdMester(), dbReview.getIdMester());
	}

	@Test
	public void testDelete_WhenSuccesfull_ThenDeleteReview() {
		ReviewMester review = createReviewMester();
		ReviewMester dbReview = reviewMesterRepository.getById(review);
		assertNotNull(dbReview);
		reviewMesterRepository.delete(dbReview);

	}

	@Test
	public void testeGetAllReviews_WhenCalled_ThenReturnPageWithReviews() {
		MesterSearchCriteria msc = createMSC();
		MyPage<ReviewMester> page = reviewMesterRepository.getAllReviewMesterPage(msc);
		assertNotNull(page);
	}

	private ReviewMester createReviewMester() {
		ReviewMester reviewMester = new ReviewMester();
		reviewMester.setId(55);
		reviewMester.setIdMester(8);
		reviewMester.setIdClient(5);
		reviewMester.setPrice("Mediu");
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
