package com.ikon.alexx.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ikon.alexx.model.MyPage; 

public abstract class BaseConverter<D, E> implements Converter<D, E> {

	public List<D> fromEntities(List<E> entities) {
		if (entities == null)
			return null;
		List<D> pojos = new ArrayList<D>();
		for (E entity : entities) {
			pojos.add(fromEntity(entity));
		}
		return pojos;
	}

	public List<E> toEntities(List<D> pojos) {
		if (pojos == null)
			return null;
		List<E> entities = new ArrayList<E>();
		for (D pojo : pojos) {
			entities.add(toEntity(pojo));
		}
		return entities;
	}
	
	public MyPage<D> fromEntitiesPage(Page<E> entitiesPage) {
		MyPage<D> dtoPage = new MyPage<D>(); 
		List<D> pojos = fromEntities(entitiesPage.getContent());
		dtoPage.setContentPage(pojos);
		dtoPage.setTotalResults((int) entitiesPage.getTotalElements()); 
		dtoPage.setPageNumber(entitiesPage.getNumber());
		dtoPage.setPageSize(entitiesPage.getSize());
		return (MyPage<D>) dtoPage;		
	}
	
	public MyPage<E> toEntitiesPage(Page<D> pojosPage) {
		MyPage<E> entityPage = new MyPage<E>();
		List<E> entitiesList = toEntities(pojosPage.getContent());
		entityPage.setContentPage(entitiesList);
		// we don't have any pojoPages
		return (MyPage<E>) entityPage;		
	}
}
