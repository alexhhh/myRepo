package com.intern.alexx.services;

import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester;

public interface ReviewMesterService {

	void insertReviewMester(ReviewMester reviewMester);

	void updateReviewMester(ReviewMester reviewMester);

	void deleteReviewMester(ReviewMester reviewMester);
	
	public ReviewMester getById(ReviewMester reviewMester);
	
	public MyPage<ReviewMester> getReviewMasterPage(Integer idMester, MesterSearchCriteria searchCriteria);

	public MyPage<ReviewMester> getReviewAllMasterPage(MesterSearchCriteria searchCriteria);
}
