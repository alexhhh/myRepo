package com.ikon.alexx.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page; 

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
	
	public Page<D> fromEntitiesPage(Page<E> entitiesPage) {
		List<D> pojos = fromEntities(entitiesPage.getContent());
		 
		return (Page<D>) pojos;		
	}
	
	public Page<E> toEntitiesPage(Page<D> pojosPage) {
		List<E> entitiesList =   toEntities(pojosPage.getContent());
		 
		return (Page<E>) entitiesList;		
	}
}
