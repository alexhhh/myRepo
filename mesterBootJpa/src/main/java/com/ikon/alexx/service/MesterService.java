package com.ikon.alexx.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ikon.alexx.model.AreaSearchCriteria;
import com.ikon.alexx.model.MesterDTO;
import com.ikon.alexx.model.MesterSearchCriteria;

public interface MesterService {

	void insertMester(MesterDTO mester);

	void updateMester(MesterDTO mester);

	void deleteMester(String mesterId);

	MesterDTO getMesterById(String id);

	Page<MesterDTO> searchMester(MesterSearchCriteria searchCriteria, Pageable pageable) throws SQLException;

	List<MesterDTO> searchMesterByArea(AreaSearchCriteria areaSearchCriteria) throws SQLException;

	void updateAvg(MesterDTO mester);

//	void insertMesterSpeciality(String specialityName, String mesterId);

//	void deleteOneMesterSpeciality(String specialityName, String mesterId);

}
