package com.aaf.fmit.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aaf.fmit.util.ConnectionManager;

public abstract class AbstractGenericDAO<T, ID> implements GenericDAO<T, ID> {
	protected abstract T mapRow(ResultSet rs) throws SQLException;

	protected abstract String getTableName();

	protected abstract String getInsertQuery();

	protected abstract String getUpdateQuery();

	protected abstract void setInsertParameters(PreparedStatement ps, T entity) throws SQLException;

	protected abstract void setUpdateParameters(PreparedStatement ps, T entity) throws SQLException;

	@Override
	public void create(T entity) {
		try (PreparedStatement ps = ConnectionManager.get().prepareStatement(getInsertQuery())) {
			setInsertParameters(ps, entity);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T retrieve(ID id) {
		T entity = null;
		try (PreparedStatement ps = ConnectionManager.get()
				.prepareStatement("SELECT * FROM " + getTableName() + " WHERE id = ?")) {
			ps.setObject(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				entity = mapRow(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public void update(T entity) {
		try (PreparedStatement ps = ConnectionManager.get().prepareStatement(getUpdateQuery())) {
			setUpdateParameters(ps, entity);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(ID id) {
		try (PreparedStatement ps = ConnectionManager.get()
				.prepareStatement("DELETE FROM " + getTableName() + " WHERE id = ?")) {
			ps.setObject(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<T> findAll() {
		List<T> entities = new ArrayList<>();
		try (PreparedStatement ps = ConnectionManager.get().prepareStatement("SELECT * FROM " + getTableName())) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				entities.add(mapRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entities;
	}
}
