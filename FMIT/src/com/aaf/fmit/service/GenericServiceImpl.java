package com.aaf.fmit.service;

import java.util.List;

import com.aaf.fmit.dao.GenericDAO;

public class GenericServiceImpl<T, ID> implements GenericService<T, ID> {

    private final GenericDAO<T, ID> dao;
    final int NOPOS = -1; // This constant is the value for non-positive.
    

    public GenericServiceImpl(GenericDAO<T, ID> dao) {
        this.dao = dao; // Inject the DAO
    }

    @Override
    public ID create(T entity) {
        return dao.create(entity); // Save the entity using DAO
    }

    @Override
    public int update(ID id, T entity) {
        T existingEntity = dao.retrieve(id); // Find the entity by ID
        if (existingEntity != null) {
            return dao.update(entity); // Update if the entity exists
        } else {
            throw new RuntimeException("Entity not found with ID: " + id);
        }
    }

    @Override
    public int delete(ID id) {
        return dao.delete(id); // Delete using DAO
    }

    @Override
    public T retrieve(ID id) {
        return dao.retrieve(id); // Get entity by ID
    }

    @Override
    public List<T> getAll() {
        return dao.findAll(); // Get all entities
    }
}
