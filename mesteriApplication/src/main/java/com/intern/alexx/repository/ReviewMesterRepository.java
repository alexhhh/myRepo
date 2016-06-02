/**
 * 
 */
package com.intern.alexx.repository;

import java.sql.SQLException;

import com.intern.alexx.model.FullReview;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester;

/**
 * @author malex
 *
 */
public interface ReviewMesterRepository {

	void insert(ReviewMester reviewMester);

	void update(ReviewMester reviewMester);

	void delete(String idReview);

	ReviewMester getById(String idReview);

	int getAvgRatingForMester(String idMester);

	int getAvgPriceForMester(String idMester);

	MyPage<ReviewMester> getAllReviewsPage(Integer pageSize, Integer pageNumber) throws SQLException;

	MyPage<ReviewMester> getAllReviewForMester(String idMester, Integer pageSize, Integer pageNumber)
			throws SQLException;

	MyPage<ReviewMester> getAllReviewFromClient(String idClient, Integer pageSize, Integer pageNumber)
			throws SQLException;

	MyPage<FullReview> getAllFullReviewsPage(Integer pageSize, Integer pageNumber) throws SQLException;

	MyPage<FullReview> getAllFullReviewsFromClient(String idClient, Integer pageSize, Integer pageNumber)
			throws SQLException;
}
