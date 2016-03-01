package com.intern.alexx.services.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester;
import com.intern.alexx.repository.ReviewMesterRepository;
import com.intern.alexx.services.ReviewMesterService;

@Component
public class ReviewMesterServiceImp implements ReviewMesterService {

	@Autowired
	private ReviewMesterRepository reviewMesterRepository;

	@Transactional
	public void insertReviewMester(ReviewMester reviewMester) {
		if (reviewMester.getId() != null){
			throw new IllegalArgumentException("review ID is not null");
		}
		reviewMesterRepository.insert(reviewMester);
	}
	
	@Transactional
	public void updateReviewMester(ReviewMester reviewMester) {
		if (reviewMester.getId() == null){
			throw new IllegalArgumentException("review ID is null");
		}
		reviewMesterRepository.update(reviewMester);
	}
	
	@Transactional
	public void deleteReviewMester(String idReview) {
		if (idReview != null){
			throw new IllegalArgumentException("review ID is null");
		}
		reviewMesterRepository.delete(idReview);
	}
	
	@Transactional
	public ReviewMester getById(String idReview) {
		return   reviewMesterRepository.getById(idReview);
	}

	public MyPage<ReviewMester> getReviewMasterPage(String idMester,Integer pageSize,Integer pageNumber) throws SQLException {
		MyPage<ReviewMester> myPage = new MyPage<ReviewMester>();
		myPage = reviewMesterRepository.getAllReviewForMester(idMester,  pageSize, pageNumber);
		return myPage;
	}
	
	public MyPage<ReviewMester> getReviewAllMasterPage(Integer pageSize,Integer pageNumber) throws SQLException {
		MyPage<ReviewMester> myPage = new MyPage<ReviewMester>();
		myPage = reviewMesterRepository.getAllReviewsPage( pageSize, pageNumber);
		return myPage;
	}
}
