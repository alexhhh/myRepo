/**
 * 
 */
package com.intern.alexx.repository;

import java.sql.SQLException;

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
	
	void updateAvg(Mester mester);  

	void delete(String mesterId);

	Mester getById(String mesterId);

	MyPage<Mester> prepareSearchForMester(MesterSearchCriteria searchCriteria) throws SQLException;

	void insertIntoMesterHasSpeciality(String mesterId,String specialityId);

	void deleteFromMesterHasSpeciality(String mesterId );
	
	void deleteOneFromMesterHasSpeciality(String mesterId,String specialityId);
}
