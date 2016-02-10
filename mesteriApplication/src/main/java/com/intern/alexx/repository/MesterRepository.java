/**
 * 
 */
package com.intern.alexx.repository;


import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
/**
 * @author malex
 *
 */
public interface MesterRepository {

	  void insert(Mester mester);
	  void update(Mester mester);
	  void delete(Mester mester);
	  Mester getById(Mester mester);
	  
	 MyPage<Mester> search(MesterSearchCriteria searchCriteria);
}
