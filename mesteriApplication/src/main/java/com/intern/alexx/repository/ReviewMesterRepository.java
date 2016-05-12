/**
 * 
 */
package com.intern.alexx.repository;

import java.sql.SQLException;

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

	public ReviewMester getById(String idReview);

	public int getAvgRatingForMester(String idMester);

	public int getAvgPriceForMester(String idMester);

	public MyPage<ReviewMester> getAllReviewsPage(Integer pageSize, Integer pageNumber) throws SQLException;

	public MyPage<ReviewMester> getAllReviewForMester(String idMester, Integer pageSize, Integer pageNumber)
			throws SQLException;

	public MyPage<ReviewMester> getAllReviewFromClient(String idClient, Integer pageSize, Integer pageNumber)
			throws SQLException;

}
