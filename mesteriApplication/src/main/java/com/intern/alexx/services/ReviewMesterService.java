package com.intern.alexx.services;

import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester;

public interface ReviewMesterService {

	void insertReviewMester(ReviewMester mester);

	void updateReviewMester(ReviewMester mester);

	void deleteReviewMester(ReviewMester mester);

	public MyPage<ReviewMester> getReviewMasterPage(MesterSearchCriteria searchCriteria);
}
