package com.ikon.alexx.service;

import java.sql.SQLException;
import java.util.List;

import com.ikon.alexx.entity.Mester;
import com.ikon.alexx.model.AreaSearchCriteria;
import com.ikon.alexx.model.FullMester;
import com.ikon.alexx.model.MesterDTO;
import com.ikon.alexx.model.MesterSearchCriteria;
import com.ikon.alexx.model.MyPage;

public interface MesterService {

	void insertMester(MesterDTO mester);
	
	void insertMester( Mester mester);

	void updateMester(MesterDTO mester);

	void updateMester(FullMester mester);
	
	void deleteMester(String mesterId);

	FullMester getMesterById(String id);
	
	FullMester getMesterByUserId(String userId);

	MyPage<FullMester> searchMester(MesterSearchCriteria searchCriteria ) throws SQLException;

	List<FullMester> searchMesterByArea(AreaSearchCriteria areaSearchCriteria) throws SQLException;
 
//	void insertMesterSpeciality(String specialityName, String mesterId);

//	void deleteOneMesterSpeciality(String specialityName, String mesterId);

}
