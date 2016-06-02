package com.intern.alexx.services;

import java.sql.SQLException;

import com.intern.alexx.model.FullReview;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester;

public interface ReviewMesterService {

	void insertReviewMester(ReviewMester reviewMester);

	void updateReviewMester(ReviewMester reviewMester);

	void deleteReviewMester(String idReview);

	ReviewMester getById(String idReview);

	float getMesterRating(String idMester);

	MyPage<ReviewMester> getReviewMasterPage(String idMester, Integer pageSize, Integer pageNumber) throws SQLException;

	MyPage<ReviewMester> getReviewAllMasterPage(Integer pageSize, Integer pageNumber) throws SQLException;

	MyPage<ReviewMester> getAllReviewFromClient(String idClient, Integer pageSize, Integer pageNumber)
			throws SQLException;

	MyPage<FullReview> getAllFullReviewsPage(Integer pageSize, Integer pageNumber) throws SQLException;

	MyPage<FullReview> getAllFullReviewsFromClient(String idClient, Integer pageSize, Integer pageNumber)
			throws SQLException;
}
