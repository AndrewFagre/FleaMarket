/**
 * @author Chris McDermit
 */
package com.aaf.fmit.dao;

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
 * @author Chris McDermit
 */
public class MarketXRefDAO extends AbstractGenericDAO<MarketXRefDO, Integer> {
	
	private static final Logger logger = LogManager.getLogger(MarketXRefDAO.class);
	private static final String INSERT_QUERY = "INSERT INTO marketxref (marketRefId, itemRefId) VALUES (?, ?)";
	private static final String UPDATE_QUERY = "UPDATE marketxref SET marketRefId = ? WHERE marketXRefId = ?";
	private static final String ITEM_MARKET_QUERY = "SELECT * FROM marketxref WHERE itemRefId = ?";
	
	private static final String TABLE_NAME = "marketxref";
	private static final String REF_ID_NAME = "marketXRefId";
	private static final String FIND_ITEM_MARKET_ERROR_MSG = "An Error occured attempting to find item market lists.";

	@Override
	protected MarketXRefDO mapRow(ResultSet rs) throws SQLException {
		logger.trace("running mapRow...");
		MarketXRefDO marketXRef = new MarketXRefDO();
		marketXRef.setMarketXRefId(rs.getInt(REF_ID_NAME));
		marketXRef.setMarketRefId(rs.getInt("marketRefId"));
		marketXRef.setItemRefId(rs.getInt("itemRefId"));
		return marketXRef;
	}

	@Override
	protected String getTableName() {
		logger.trace("running getTableName...");
		return TABLE_NAME;
	}

	@Override
	protected String getInsertQuery() {
		logger.trace("running getInsertQuery...");
		return INSERT_QUERY;
	}

	@Override
	protected String getUpdateQuery() {
		logger.trace("running getUpdateQuery...");
		return UPDATE_QUERY;
	}

	@Override
	protected void setInsertParameters(PreparedStatement ps, MarketXRefDO entity) throws SQLException {
		logger.trace("running setInsertParameters...");
		ps.setInt(1, entity.getMarketRefId());
		ps.setInt(2, entity.getItemRefId());
	}

	@Override
	protected void setUpdateParameters(PreparedStatement ps, MarketXRefDO entity) throws SQLException {
		logger.trace("running setUpdateParameters...");
		ps.setInt(1, entity.getMarketRefId());
		ps.setInt(2, entity.getMarketXRefId());
	}

	@Override
	protected String getRefIdName() {
		logger.trace("running getRefIdName...");
		return REF_ID_NAME;
	}
	
	/**
	 * Gets the markets for the item ref passed in.
	 * 
	 * @param itemRefId the reference for the item
	 * @return the associated markets
	 */
	public List<MarketXRefDO> getItemMarketList(int itemRefId) {
		logger.trace("running getItemMarketList...");

		List<MarketXRefDO> returnList = new ArrayList<>();;
		
		try (Connection connection = ConnectionManager.get();
				PreparedStatement ps = connection.prepareStatement(ITEM_MARKET_QUERY)) {
				ps.setInt(1, itemRefId);	
			try (ResultSet rs = ps.executeQuery()) { // using try blocks to free resources reliable
				while (rs.next()) {
					returnList.add(mapRow(rs)); // Process each row
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(FIND_ITEM_MARKET_ERROR_MSG, e);
		}
		return returnList;
	}
}
