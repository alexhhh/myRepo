/**
 * 
 */
package com.intern.alexx.services;



import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;

/**
 * @author malex
 *
 */
public interface MesterService {

	void insertMester(Mester mester);

	void updateMester(Mester mester);

	void deleteMester(Mester mester);

	MyPage searchMester(MesterSearchCriteria searchCriteria);

	// TODO add crud methods

}
