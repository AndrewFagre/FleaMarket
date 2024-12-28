package com.aaf.fmit.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractGenericDAO<UserDO, Integer> {
	
	@Override
	protected UserDO mapRow(ResultSet rs) throws SQLException {
		UserDO user = new UserDO();
		user.setUserRefId(rs.getInt("userRefId"));
		user.setName(rs.getString("name"));
		return user;
	}

	@Override
	protected String getTableName() {
		return "user";
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO user (userRefId, name) VALUES (?, ?)";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE users SET userRefId = ?, name = ? WHERE id = ?";
	}

	@Override
	protected void setInsertParameters(PreparedStatement ps, UserDO user) throws SQLException {
		ps.setInt(1, user.getUserRefId());
		ps.setString(2, user.getName());
	}

	@Override
	protected void setUpdateParameters(PreparedStatement ps, UserDO user) throws SQLException {
		ps.setInt(1, user.getUserRefId());
		ps.setString(2, user.getName());
	}
}
