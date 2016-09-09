package com.ikon.alexx.service;

import java.sql.SQLException;
import java.util.List;
 
import com.ikon.alexx.model.AreaSearchCriteria;
import com.ikon.alexx.model.MesterDTO;
import com.ikon.alexx.model.MesterSearchCriteria;
import com.ikon.alexx.model.MyPage;

public interface MesterService {

	void insertMester(MesterDTO mester);

	void updateMester(MesterDTO mester);

	void deleteMester(String mesterId);

	MesterDTO getMesterById(String id);
	
	MesterDTO getMesterByUserId(String userId);

	MyPage<MesterDTO> searchMester(MesterSearchCriteria searchCriteria ) throws SQLException;

	List<MesterDTO> searchMesterByArea(AreaSearchCriteria areaSearchCriteria) throws SQLException;
 
//	void insertMesterSpeciality(String specialityName, String mesterId);

//	void deleteOneMesterSpeciality(String specialityName, String mesterId);

}
