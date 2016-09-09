package com.ikon.alexx.repository;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ikon.alexx.entity.Mester;
import com.ikon.alexx.model.AreaSearchCriteria;
import com.ikon.alexx.model.MesterSearchCriteria;

public interface MesterSpecialQueryRepository {
	
	Page<Mester> searchForMester(MesterSearchCriteria searchCriteria);
	
	List<Mester> searchMesterByArea(AreaSearchCriteria areaSearchCriteria);

}
