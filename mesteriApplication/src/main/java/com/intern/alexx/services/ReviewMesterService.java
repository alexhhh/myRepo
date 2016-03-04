package com.intern.alexx.services;

import java.sql.SQLException;
 
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester;

public interface ReviewMesterService {

	void insertReviewMester(ReviewMester reviewMester);

	void updateReviewMester(ReviewMester reviewMester);

	void deleteReviewMester(String idReview);
	
	public ReviewMester getById(String idReview);
	
	public float getMesterRating(String idReview);
	
	public MyPage<ReviewMester> getReviewMasterPage(String idMester, Integer pageSize,Integer pageNumber) throws SQLException;

	public MyPage<ReviewMester> getReviewAllMasterPage(Integer pageSize,Integer pageNumber) throws SQLException;
}
