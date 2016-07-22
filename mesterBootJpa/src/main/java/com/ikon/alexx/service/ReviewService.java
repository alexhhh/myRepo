package com.ikon.alexx.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ikon.alexx.model.ReviewDTO;

public interface ReviewService {

	void insertReviewMester(ReviewDTO reviewMester);

	void updateReviewMester(ReviewDTO reviewMester);

	void deleteReviewMester(String idReview);

	ReviewDTO getById(String idReview);

	// float getMesterRating(String idMester);

	Page<ReviewDTO> getReviewMasterPage(String idMester, Pageable pageable);

	Page<ReviewDTO> getReviewAllMasterPage(Pageable pageable);

	Page<ReviewDTO> getAllReviewFromClient(String idClient, Pageable pageable);

	// Page<FullReview> getAllFullReviewsPage(Pageable pageable);

	// Page<FullReview> getAllFullReviewsFromClient(String idClient, Pageable
	// pageable);
}
