package com.ikon.alexx.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.ikon.alexx.entity.Mester;
import com.ikon.alexx.model.AreaSearchCriteria;
import com.ikon.alexx.model.MesterSearchCriteria;

public class MesterRepositoryImpl implements MesterSpecialQueryRepository {

	@Autowired
	EntityManager em;

	@Autowired
	private GenerateSql generateSql;

	@Override
	public List<Mester> searchMesterByArea(AreaSearchCriteria areaSearchCriteria) {
		TypedQuery<Mester> query = em.createQuery(createQueryForAreaSearch(areaSearchCriteria), Mester.class);
		query.setParameter(1, areaSearchCriteria.getMinLat());
		query.setParameter(2, areaSearchCriteria.getMaxLat());
		query.setParameter(3, areaSearchCriteria.getMinLng());
		query.setParameter(4, areaSearchCriteria.getMaxLng());
		return query.getResultList();
	}

	@Override
	public Page<Mester> searchForMester(MesterSearchCriteria searchCriteria) {
		Pageable pageable = createPageRequest(searchCriteria);
		//TypedQuery<Mester> query = em.createQuery(generateSql.createQueryForElements(searchCriteria), Mester.class); //;
		List<Mester> mesterList = prepareSearchForMester(searchCriteria);

		Long total =  countSearchMester(searchCriteria);
		//if (null == pageable) {
		//	return new PageImpl<Mester>(prepareSearchForMester(searchCriteria));
		//}
		//TypedQuery<Mester> query =TypedQuery em.Get
		//return pageable == null ? new PageImpl<Mester>(query.getResultList()) : readPage(query, pageable, spec);
		return new PageImpl<>(mesterList, pageable, total); 
	}

	private Pageable createPageRequest(MesterSearchCriteria searchCriteria) {
		if (searchCriteria.getPageSize() == null) {
			searchCriteria.setPageSize(10);
		}
		if (searchCriteria.getPageNumber() == null) {
			searchCriteria.setPageNumber(1);
		}
		return new PageRequest(searchCriteria.getPageNumber(), searchCriteria.getPageSize());
	}


	
	private List<Mester> prepareSearchForMester(MesterSearchCriteria searchCriteria) {
		int skip= searchCriteria.getPageSize()*(searchCriteria.getPageNumber() );
		TypedQuery<Mester> query = em.createQuery(generateSql.createQueryForElements(searchCriteria), Mester.class).setFirstResult(skip).setMaxResults(searchCriteria.getPageSize());
		Map<String, Object> queryParams =generateSql.createQueryParam(searchCriteria);
		for (String param :queryParams.keySet()) {
			query.setParameter(param, queryParams.get(param));		
		};		
		return query.getResultList();
	}

	private Long countSearchMester(MesterSearchCriteria searchCriteria) {
		TypedQuery<Long> query = em.createQuery(generateSql.createQueryForCountElements(searchCriteria), Long.class);
		Map<String, Object> queryParams =generateSql.createQueryParam(searchCriteria);
		for (String param :queryParams.keySet()) {
			query.setParameter(param, queryParams.get(param));		
		};	
		return query.getSingleResult();
	}
	
	
	private String createQueryForAreaSearch(AreaSearchCriteria areaSearchCriteria) {
		return "SELECT m FROM Mester  m  left outer join  m.location  l  WHERE "
				+ "( l.latitude BETWEEN ?1 AND ?2 )  AND  ( l.longitude  BETWEEN  ?3  AND ?4 )";
	}
}
