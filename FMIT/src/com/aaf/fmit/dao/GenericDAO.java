package com.aaf.fmit.dao;

import java.util.List;

public interface GenericDAO<T, ID> {
	ID create(T entity);

	T retrieve(ID id);

	int update(T entity);

	int delete(ID id);

	List<T> findAll();
}
