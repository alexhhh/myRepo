package com.ikon.alexx.service;

import com.ikon.alexx.model.FullReview;
import com.ikon.alexx.model.MyPage;
import com.ikon.alexx.model.ReviewDTO;

public interface ReviewService {

	void insertReviewMester(ReviewDTO reviewMester);

	void updateReviewMester(ReviewDTO reviewMester);

	void deleteReviewMester(String idReview);

	ReviewDTO getById(String idReview);

	// float getMesterRating(String idMester);

	MyPage<ReviewDTO> getReviewMasterPage(String idMester, Integer pageSize, Integer pageNumber);

	MyPage<ReviewDTO> getReviewAllMasterPage(Integer pageSize, Integer pageNumber);

	MyPage<ReviewDTO> getAllReviewFromClient(String idClient, Integer pageSize, Integer pageNumber);

	MyPage<FullReview> getAllFullReviewsPage( Integer pageSize, Integer pageNumber);

	MyPage<FullReview> getAllFullReviewsFromClient(String idClient, Integer pageSize, Integer pageNumber);
	 
}
