package com.ikon.alexx.converters;

public interface Converter<D, E> {

	E toEntity(D pojo);

	D fromEntity(E entity);

}
