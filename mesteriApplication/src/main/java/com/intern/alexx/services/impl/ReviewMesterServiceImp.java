package com.intern.alexx.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester;
import com.intern.alexx.repository.ReviewMesterRepository;
import com.intern.alexx.services.ReviewMesterService;

public class ReviewMesterServiceImp implements ReviewMesterService {

	@Autowired
	private ReviewMesterRepository reviewMesterRepository;

	public void insertReviewMester(ReviewMester reviewMester) {
		reviewMesterRepository.insert(reviewMester);
	}

	public void updateReviewMester(ReviewMester reviewMester) {
		reviewMesterRepository.update(reviewMester);
	}

	public void deleteReviewMester(ReviewMester reviewMester) {
		reviewMesterRepository.delete(reviewMester);
	}

	public MyPage<ReviewMester> getReviewMasterPage(MesterSearchCriteria searchCriteria) {
		MyPage<ReviewMester> myPage = new MyPage<ReviewMester>();
		myPage = reviewMesterRepository.getAllReviewMesterPage(searchCriteria);
		return myPage;
	}

	public ReviewMester getById(ReviewMester reviewMester) {
		reviewMesterRepository.getById(reviewMester);
		return reviewMester;
	}

}
