package com.aaf.fmit.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDAO extends AbstractGenericDAO<UserDO, Integer> {
	
	private static final Logger logger = LogManager.getLogger(UserDAO.class);
	
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
		return "INSERT INTO user (name) VALUES (?)";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE user SET name = ? WHERE userRefId = ?";
	}

	@Override
	protected void setInsertParameters(PreparedStatement ps, UserDO user) throws SQLException {
		ps.setString(1, user.getName());
	}

	@Override
	protected void setUpdateParameters(PreparedStatement ps, UserDO user) throws SQLException {
		ps.setString(1, user.getName());
		ps.setInt(2, user.getUserRefId());
	}

	@Override
	protected String getRefIdName() {
		logger.trace("running getRefIdName...");
		return "userRefId";
	}
}
