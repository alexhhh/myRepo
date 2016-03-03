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

	ReviewMester getById(String idReview);
	
	public float getAvgRatingForMester(String idReview);

	MyPage<ReviewMester> getAllReviewsPage(Integer pageSize,Integer pageNumber) throws SQLException;
	
	MyPage<ReviewMester> getAllReviewForMester(String idMester, Integer pageSize,Integer pageNumber) throws SQLException;

}
