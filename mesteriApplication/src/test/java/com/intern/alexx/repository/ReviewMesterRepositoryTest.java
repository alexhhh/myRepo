package com.intern.alexx.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(GenerateSqlTest.class);

//	@Test
//	public void canInsertReviewInDB() {
//
//		ReviewMester review = createReviewMester();
//		reviewMesterRepository.insert(review);
//		ReviewMester dbReview = reviewMesterRepository.getById(review);
//		assertNotNull(dbReview);
//		assertEquals(review.getIdMester(), dbReview.getIdMester());
//		logger.info("Review-ul returnat e: ");
//		logger.info(review.toString());
//	}
//
//	@Test
//	public void canDeleteReviewFromDB() {
//
//		ReviewMester review = new ReviewMester();
//		review.setId(15);
//		ReviewMester dbReview = reviewMesterRepository.getById(review);
//		assertNotNull(dbReview);
//		reviewMesterRepository.delete(dbReview);
//
//	}
//
//	@Test
//	public void canUpdateReviewFromDB() {
//
//		ReviewMester review = createReviewMester();
//		ReviewMester dbReview = reviewMesterRepository.getById(review);
//		assertNotNull(dbReview);
//		reviewMesterRepository.update(review);
//		assertEquals(review.getIdMester(), dbReview.getIdMester());
//		logger.info("Review-ul returnat e: ");
//		logger.info(dbReview.toString());
//
//	}
//
//	@Test
//	public void returnAllReviews() {
//
//		logger.info("Return all reviews .ok pana aici.");
//		MesterSearchCriteria msc = createMSC();
//		MyPage<ReviewMester> page = reviewMesterRepository.getAll(msc);
//		assertNotNull(page);
//		logger.info(page.getPageSize().toString());
//		logger.info("Page-ul returnat e: ");
//		logger.info(page.toString());
//	}
//
//	private ReviewMester createReviewMester() {
//
//		ReviewMester reviewMester = new ReviewMester();
//
//		reviewMester.setId(5);
//		reviewMester.setIdMester(8);
//		reviewMester.setIdClient(5);
//		reviewMester.setPrice("Mediu");
//		reviewMester.setRating(5);
//		reviewMester.setFeedback("Awesome Update");
//
//		return reviewMester;
//	}
//
//	private MesterSearchCriteria createMSC() {
//		MesterSearchCriteria msc = new MesterSearchCriteria();
//		msc.setPageNumber(3);
//		msc.setPageSize(2);
//		return msc;
//	}

}
