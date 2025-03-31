package com.aaf.fmit.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aaf.fmit.util.ConnectionManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @param <T>  DO entity
 * @param <ID> Primary key Data type
 */
public abstract class AbstractGenericDAO<T, ID> implements GenericDAO<T, ID> {

	private static final Logger logger = LogManager.getLogger(AbstractGenericDAO.class);

	protected abstract T mapRow(ResultSet rs) throws SQLException;

	protected abstract String getTableName();

	protected abstract String getInsertQuery();

	protected abstract String getUpdateQuery();

	protected abstract String getRefIdName();

	protected abstract void setInsertParameters(PreparedStatement ps, T entity) throws SQLException;

	protected abstract void setUpdateParameters(PreparedStatement ps, T entity) throws SQLException;

	private static final String CREATE_ERROR_MSG = "An Error occured attempting to create an entity.";
	private static final String RETRIEVE_ERROR_MSG = "An Error occured attempting to retrieve an entity.";
	private static final String UPDATE_ERROR_MSG = "An Error occured attempting to update an entity.";
	private static final String DELETE_ERROR_MSG = "An Error occured attempting to delete an entity.";
	private static final String FIND_ALL_ERROR_MSG = "An Error occured attempting to find all entities.";
	private static final int NON_POSITIVE = -1;

	/**
	 * 
	 * @param entity the entity to set
	 *
	 * @return the newly created entity's ID
	 */
	public ID create(T entity) {
		logger.trace("running create()");
		ID returnValue = null;
		try (PreparedStatement ps = ConnectionManager.get().prepareStatement(getInsertQuery(),
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			setInsertParameters(ps, entity);
			logger.trace("Executing query: " + getInsertQuery() + " with parameters: " + entity.toString());
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected == 1) { // success!
				// Retrieve the generated key (new ID)
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						BigInteger bigIntegerValue = (BigInteger) generatedKeys.getObject(1); // Assuming the ID is the first column
						Integer intValue = bigIntegerValue.intValue(); // Safe conversion
						returnValue = (ID) intValue; 
					} else {
						// the reason for potential failure here is unknown, however if the scenario
						// exists we have it covered.
						throw new SQLException("Failed to retrieve the generated ID.");
					} // close if else
				} // close try with ResultSet
			} else if (rowsAffected > 1) {
				// Unexpected: More than one row updated
				throw new SQLException("Update query for creation affected multiple rows (rowsAffected=" + rowsAffected
						+ "). Possible data corruption.");
			} else if (rowsAffected < 1) {
				// No rows affected: Entity not found or query conditions failed
				throw new SQLException("Update query for creation affected no rows (rowsAffected=" + rowsAffected
						+ "). Check query conditions.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(CREATE_ERROR_MSG, e);
		}
		return returnValue;
	}

	@Override
	public T retrieve(ID id) {
		logger.trace("running retrieve()");
		T entity = null;
		try (PreparedStatement ps = ConnectionManager.get()
				.prepareStatement("SELECT * FROM " + getTableName() + " WHERE " + getRefIdName() + " = ?")) {
			ps.setObject(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					entity = mapRow(rs); // Map the row to an entity
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(RETRIEVE_ERROR_MSG, e);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int update(T entity) {
		logger.trace("running update()");
		Integer rowsAffected = NON_POSITIVE;
		try (Connection connection = ConnectionManager.get();
				PreparedStatement ps = connection.prepareStatement(getUpdateQuery())) {
			setUpdateParameters(ps, entity);
			logger.trace("Executing query: " + getUpdateQuery() + " with parameters: " + entity.toString());
//			System.out.println("Executing query: " + getUpdateQuery() + " with parameters: " + entity.toString());
			rowsAffected = ps.executeUpdate();
			if (rowsAffected == 1) {
				return rowsAffected;// Success: Exactly one row updated
			} else if (rowsAffected > 1) {
				// Unexpected: More than one row updated
				throw new SQLException("Update query affected multiple rows (rowsAffected=" + rowsAffected
						+ "). Possible data corruption.");
			} else if (rowsAffected < 1) {
				// No rows affected: Entity not found or query conditions failed
				throw new SQLException(
						"Update query affected no rows (rowsAffected=" + rowsAffected + "). Check query conditions.");
			}

		} catch (SQLException e) {
			throw new RuntimeException(UPDATE_ERROR_MSG, e);
		}
		return rowsAffected;
	}

	@Override
	public int delete(ID id) {
		logger.trace("running delete()"); // hard delete
		int rowsAffected = NON_POSITIVE;
		try (Connection connection = ConnectionManager.get();
				PreparedStatement ps = connection
						.prepareStatement("DELETE FROM " + getTableName() + " WHERE " + getRefIdName() + " = ?")) {
			ps.setObject(1, id);
			rowsAffected = ps.executeUpdate();
			if (rowsAffected == 1) {
				return rowsAffected;
			} else if (rowsAffected > 1) {
				// Unexpected: More than one row updated
				throw new SQLException("Update query for deletion affected multiple rows (rowsAffected=" + rowsAffected
						+ "). Possible data corruption.");
			} else if (rowsAffected < 1) {
				// No rows affected: Entity not found or query conditions failed
				throw new SQLException("Update query for deletion affected no rows (rowsAffected=" + rowsAffected
						+ "). Check query conditions.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(DELETE_ERROR_MSG, e);
		}
		return rowsAffected;
	}

	@Override
	public List<T> findAll() {
		logger.trace("running findAll()");
		List<T> entities = new ArrayList<>();

		try (Connection connection = ConnectionManager.get();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + getTableName())) {
			try (ResultSet rs = ps.executeQuery()) { // using try blocks to free resources reliable
				while (rs.next()) {
					entities.add(mapRow(rs)); // Process each row
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(FIND_ALL_ERROR_MSG, e);
		}
		return entities;
	}
	
	/**
	 * Utility method to set parameters dynamically allowing the parm to be null when source value not present.
	 * 
	 * @param ps the statement to be used
	 * @param parameterIndex which index for the parm
	 * @param value the value to set the parm to
	 * @param sqlType the type of value expected for the parm
	 * 
	 * @throws SQLException
	 */
	public static void setParameter(PreparedStatement ps, int parameterIndex, Object value, int sqlType) throws SQLException {
	    if (value == null) {
	        ps.setNull(parameterIndex, sqlType); // Automatically sets to NULL if value is null
	    } else {
	        if (value instanceof String) {
	            ps.setString(parameterIndex, (String) value);
	        } else if (value instanceof Integer) {
	            ps.setInt(parameterIndex, (Integer) value);
	        } else if (value instanceof Double) {
	        	ps.setDouble(parameterIndex, (Double) value);
	        } else if (value instanceof java.sql.Date) {
	        	ps.setDate(parameterIndex, (java.sql.Date) value);
	        } else if (value instanceof java.util.Date) {
	            ps.setDate(parameterIndex, new java.sql.Date(((java.util.Date) value).getTime()));
	        } else if (value instanceof java.time.LocalDate) {
	            ps.setDate(parameterIndex, java.sql.Date.valueOf((java.time.LocalDate) value));
	        } else {
	            throw new IllegalArgumentException("Unsupported type: " + value.getClass());
	        }
	    }
	}
}
