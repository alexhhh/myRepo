package com.ikon.alexx.repository;

import java.util.List;
 
import org.springframework.data.domain.Page;  
import com.ikon.alexx.entity.Mester;
import com.ikon.alexx.model.MesterSearchCriteria;

public class FrecareaPaginii {

	public List<Mester> prepareSearchForMester(MesterSearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Mester> searchForMester(MesterSearchCriteria searchCriteria ) {
	//	 Pageable pageable = createPageRequest( searchCriteria );
		//PageRequest pageReq = new PageRequest(searchCriteria.getPageNumber(), searchCriteria.getPageSize());
//		if (null == pageable) {
//			return new PageImpl<Mester>(prepareSearchForMester(searchCriteria));
//		}
//		TypedQuery<Mester> query = getQuery(spec, pageable);
//		return pageable == null ? new PageImpl<Mester>(query.getResultList()) : readPage(query, pageable, spec);
		return null;
	}
		 
	 

//	private Pageable createPageRequest(MesterSearchCriteria searchCriteria ) {
//		return new PageRequest(searchCriteria.getPageNumber(), searchCriteria.getPageSize());
//	}
}
