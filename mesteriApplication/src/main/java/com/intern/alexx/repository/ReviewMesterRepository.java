/**
 * 
 */
package com.intern.alexx.repository;


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
	  void delete(ReviewMester reviewMester);
	  ReviewMester getById(ReviewMester reviewMester);
	  MyPage<ReviewMester> getAll(MesterSearchCriteria searchCriteria);

}
 