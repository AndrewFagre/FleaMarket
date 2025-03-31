/**
 * @author Chris McDermit
 */
package com.aaf.fmit.service;

import java.util.List;

/**
 * @author Chris McDermit
 */
public interface GenericService<T, ID> {
	    ID create(T entity); // Create
	    int update(ID id, T entity); // Update
	    int delete(ID id); // Delete
	    T retrieve(ID id); // Read single record
	    List<T> getAll(); // Read all records
}
