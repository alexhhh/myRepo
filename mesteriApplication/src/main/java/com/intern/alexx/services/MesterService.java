package com.intern.alexx.services;

import java.sql.SQLException;
import java.util.List;

import com.intern.alexx.model.AreaSearchCriteria;
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

	Mester getById(String id);

	Mester getMesterById(String mesterId) throws SQLException;

	MyPage<Mester> searchMester(MesterSearchCriteria searchCriteria) throws SQLException;

	List<Mester> searchMesterByArea(AreaSearchCriteria areaSearchCriteria) throws SQLException;

	void insert(Mester mester);

	void update(Mester mester);

	void delete(String mesterId);

	void updateAvg(Mester mester);

	void insertMesterSpeciality(String specialityName, String mesterId);

	void deleteOneMesterSpeciality(String specialityName, String mesterId);

}
