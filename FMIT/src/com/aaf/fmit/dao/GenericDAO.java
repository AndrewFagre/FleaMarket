package com.aaf.fmit.dao;

import java.util.List;

public interface GenericDAO<T, ID> {
	void create(T entity);

	T retrieve(ID id);

	void update(T entity);

	void delete(ID id);

	List<T> findAll();
}
