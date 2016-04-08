package com.intern.alexx.services.impl;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester;
import com.intern.alexx.repository.MesterRepository;
import com.intern.alexx.repository.ReviewMesterRepository;
import com.intern.alexx.services.ReviewMesterService;

@Component
public class ReviewMesterServiceImp implements ReviewMesterService {

	@Autowired
	private ReviewMesterRepository reviewMesterRepository;
	
	private static Logger LOGGER = LoggerFactory.getLogger(ReviewMesterServiceImp.class);
	

	@Autowired
	private MesterRepository mesterRepository;
	
	@Transactional
	public void insertReviewMester(ReviewMester reviewMester) {
//		if (reviewMester.getId() != null) {
//			throw new IllegalArgumentException("review ID is not null");
//		}
		reviewMesterRepository.insert(reviewMester);
		updateMesterAvgPriceAndRating(reviewMester.getIdMester());
	}

	@Transactional
	public void updateReviewMester(ReviewMester reviewMester) {
//		if (reviewMester.getId() == null) {
//			throw new IllegalArgumentException("review ID is null");
//		}
		reviewMesterRepository.update(reviewMester);
		updateMesterAvgPriceAndRating(reviewMester.getIdMester());
	}

	@Transactional
	public void deleteReviewMester(String idReview) {
		if (idReview == null) {
			throw new IllegalArgumentException("review ID is null");
		}
		reviewMesterRepository.delete(idReview);
		ReviewMester review =  getById(idReview);
		updateMesterAvgPriceAndRating(review.getIdMester());
	}

	@Transactional
	public ReviewMester getById(String idReview) {
		return reviewMesterRepository.getById(idReview);
	}
	
	@Transactional
	public MyPage<ReviewMester> getReviewMasterPage(String idMester, Integer pageSize, Integer pageNumber)
			throws SQLException {
		MyPage<ReviewMester> myPage = new MyPage<ReviewMester>();
		myPage = reviewMesterRepository.getAllReviewForMester(idMester, pageSize, pageNumber);
		return myPage;
	}
	
	@Transactional
	public MyPage<ReviewMester> getReviewAllMasterPage(Integer pageSize, Integer pageNumber) throws SQLException {
		MyPage<ReviewMester> myPage = new MyPage<ReviewMester>();
		myPage = reviewMesterRepository.getAllReviewsPage(pageSize, pageNumber);
		return myPage;
	}

	@Transactional
	public float getMesterRating(String idMester) {
		return reviewMesterRepository.getAvgRatingForMester(idMester);
	}
	
	private void updateMesterAvgPriceAndRating(String idMester){
		Mester mester=mesterRepository.getById(idMester);
		LOGGER.info("--before---"+ mester.toString()+"---------");
		mester.setAvgRating(reviewMesterRepository.getAvgRatingForMester(idMester));
		mester.setAvgPrice(reviewMesterRepository.getAvgPriceForMester(idMester));
		mesterRepository.updateAvg(mester);
		LOGGER.info("--after---"+ mester.toString()+"---------");
	}
	
}
