package com.intern.alexx.services;


 
import java.sql.SQLException;
 
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

	void deleteMester(String mesterId);
	
	public Mester getById(String id);
	
	public Mester getMesterById(String mesterId) throws SQLException ;

	MyPage<Mester> searchMester(MesterSearchCriteria searchCriteria) throws SQLException;
	
 
}
