/**
 * @author Chris McDermit
 */
package com.aaf.fmit.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Chris McDermit
 */
public class MarketDAO extends AbstractGenericDAO<MarketDO, Integer> {
	
	private static final Logger logger = LogManager.getLogger(MarketDAO.class);

	@Override
	protected MarketDO mapRow(ResultSet rs) throws SQLException {
		logger.trace("running mapRow...");
		MarketDO market = new MarketDO();
		market.setMarketRefId(rs.getInt("marketRefId"));
		market.setName(rs.getString("name"));
		return market;
	}

	@Override
	protected String getTableName() {
		logger.trace("running getTableName...");
		return "market";
	}

	@Override
	protected String getInsertQuery() {
		logger.trace("running getInsertQuery...");
		return "INSERT INTO market (name) VALUES (?)";
	}

	@Override
	protected String getUpdateQuery() {
		logger.trace("running getUpdateQuery...");
		return "UPDATE market SET name = ? WHERE marketRefId = ?";
	}

	@Override
	protected void setInsertParameters(PreparedStatement ps, MarketDO entity) throws SQLException {
		logger.trace("running setInsertParameters...");
		ps.setString(1, entity.getName());
	}

	@Override
	protected void setUpdateParameters(PreparedStatement ps, MarketDO entity) throws SQLException {
		logger.trace("running setUpdateParameters...");	
		ps.setString(1, entity.getName());
		ps.setInt(2, entity.getMarketRefId());
	}

	@Override
	protected String getRefIdName() {
		logger.trace("running getRefIdName...");
		return "marketRefId";
	}
}
