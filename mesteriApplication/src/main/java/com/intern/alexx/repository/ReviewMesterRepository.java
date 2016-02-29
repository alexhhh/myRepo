/**
 * 
 */
package com.intern.alexx.repository;


import java.sql.SQLException;

import com.intern.alexx.model.MesterSearchCriteria;
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

	MyPage<ReviewMester> getAllReviewsPage(MesterSearchCriteria searchCriteria) throws SQLException;
	
	MyPage<ReviewMester> getAllReviewForMester(String idMester, MesterSearchCriteria searchCriteria) throws SQLException;

}
