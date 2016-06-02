package com.intern.alexx.services.impl;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intern.alexx.model.FullReview;
import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester; 
import com.intern.alexx.repository.ReviewMesterRepository;
import com.intern.alexx.services.MesterService;
import com.intern.alexx.services.ReviewMesterService;

@Component
public class ReviewMesterServiceImp implements ReviewMesterService {

	private static Logger LOGGER = LoggerFactory.getLogger(ReviewMesterServiceImp.class);

	@Autowired
	private ReviewMesterRepository reviewMesterRepository;

	@Autowired
	private MesterService mesterService;

	@Transactional
	public void insertReviewMester(ReviewMester reviewMester) {
		// if (reviewMester.getId() != null) {
		// throw new IllegalArgumentException("review ID is not null");
		// }
		reviewMesterRepository.insert(reviewMester);
		LOGGER.info("--Service--Insert review: " + reviewMester.toString());
		updateMesterAvgPriceAndRating(reviewMester.getIdMester());
	}

	@Transactional
	public void updateReviewMester(ReviewMester reviewMester) {
		// if (reviewMester.getId() == null) {
		// throw new IllegalArgumentException("review ID is null");
		// }
		reviewMesterRepository.update(reviewMester);
		LOGGER.info("--Service--Update review: " + reviewMester.toString());
		updateMesterAvgPriceAndRating(reviewMester.getIdMester());
	}

	@Transactional
	public void deleteReviewMester(String idReview) {
		if (idReview == null) {
			throw new IllegalArgumentException("review ID is null");
		}
		reviewMesterRepository.delete(idReview);
		LOGGER.info("--Service--Update review with id: " + idReview);
		updateMesterAvgPriceAndRating(getById(idReview).getIdMester());
	}

	@Transactional
	public ReviewMester getById(String idReview) {
		LOGGER.info("--Service--Get review with id: " + idReview);
		return reviewMesterRepository.getById(idReview);
	}

	@Transactional
	public MyPage<ReviewMester> getReviewMasterPage(String idMester, Integer pageSize, Integer pageNumber)
			throws SQLException {
		LOGGER.info("--Service--Get a page with user reviews");
		return reviewMesterRepository.getAllReviewForMester(idMester, pageSize, pageNumber);
	}

	@Transactional
	public MyPage<ReviewMester> getReviewAllMasterPage(Integer pageSize, Integer pageNumber) throws SQLException {
		LOGGER.info("--Service--Get a page with reviews");
		return reviewMesterRepository.getAllReviewsPage(pageSize, pageNumber);
	}

	@Transactional
	public MyPage<ReviewMester> getAllReviewFromClient(String idClient, Integer pageSize, Integer pageNumber)
			throws SQLException {
		LOGGER.info("--Service--Get a page with reviews from crient");
		return reviewMesterRepository.getAllReviewFromClient(idClient, pageSize, pageNumber);
	}

	@Transactional
	public float getMesterRating(String idMester) {
		LOGGER.info("--Service--Get mester rating");
		return reviewMesterRepository.getAvgRatingForMester(idMester);
	}

	@Transactional
	public MyPage<FullReview> getAllFullReviewsPage(Integer pageSize, Integer pageNumber) throws SQLException {
		LOGGER.info("--Service--Get a page with mester full reviews");
		return reviewMesterRepository.getAllFullReviewsPage(pageSize, pageNumber);
	}

	@Transactional
	public MyPage<FullReview> getAllFullReviewsFromClient(String idClient, Integer pageSize, Integer pageNumber)
			throws SQLException {
		LOGGER.info("--Service--Get a page with full reviews from crient");
		return reviewMesterRepository.getAllFullReviewsFromClient(idClient, pageSize, pageNumber);
	}

	private void updateMesterAvgPriceAndRating(String idMester) {
		Mester mester = new Mester();
		try {
			mester = mesterService.getMesterById(idMester);
			LOGGER.info("--before---" + mester.toString() + "---------");
			mester.setAvgRating(reviewMesterRepository.getAvgRatingForMester(idMester));
			mester.setAvgPrice(reviewMesterRepository.getAvgPriceForMester(idMester));
			mesterService.updateAvg(mester);
			LOGGER.info("--after---" + mester.toString() + "---------");
		} catch (SQLException e) {			 
			e.printStackTrace();
		}		
	}
}
