/**
 * 
 */
package com.intern.alexx.repository;
import java.util.List;

import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
/**
 * @author malex
 *
 */
public interface MesterRepository {

	  void insert(Mester mester);
	  void update(Mester mester);
	  void delete(Mester mester);
	  Mester getById(Mester mester);
	  List<Mester> findAll();
	  
	  List<Mester> getByLocation(Mester mester); // o sa fie dincolo
	  List<Mester> search(MesterSearchCriteria searchCriteria);
}
