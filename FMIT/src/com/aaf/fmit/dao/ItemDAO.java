/**
 * @author Chris McDermit
 */
package com.aaf.fmit.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Chris McDermit
 */
public class ItemDAO extends AbstractGenericDAO<ItemDO, Integer> {
	
	private static final Logger logger = LogManager.getLogger(ItemDAO.class);

	private static final String INSERT_ITEM_QUERY = 
		    "INSERT INTO `item` " +
		    "(`name`, `description`, `barCode`, `sellerRefId`, " +
		    "`commentRefId`, `boughtDate`, `soldDate`, `boughtPrice`, `soldPrice`) " +
		    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE_ITEM_QUERY = "UPDATE `item` " +
	        "SET `name` = ?, " +
	        "`description` = ?, " +
	        "`barCode` = ?, " +
	        "`sellerRefId` = ?, " +
	        "`commentRefId` = ?, " +
	        "`boughtDate` = ?, " +
	        "`soldDate` = ?, " +
	        "`boughtPrice` = ?, " +
	        "`soldPrice` = ? " +
	        "WHERE `itemRefId` = ?";

	/**
	 * Constructs a ItemDAO 
	 */
	public ItemDAO() {}

	@Override
	protected ItemDO mapRow(ResultSet rs) throws SQLException {
		logger.trace("running mapRow...");
		
		ItemDO item = new  ItemDO();
		Integer doItemRefId;
		String doName;
		String doDescription;
		Integer doBarCode;
		Integer doSellerRefId;
		Integer doCommentRefId;
		Date doBoughtDate;
		Date doSoldDate;
		Double doBoughtPrice;
		Double doSoldPrice;
		// extracts record data in single logical step
		doItemRefId = rs.getInt(getRefIdName());
		doName = rs.getString("name");
		doDescription = rs.getString("description");
		doBarCode = rs.getInt("barCode");
		doSellerRefId = rs.getInt("sellerRefId");
		doCommentRefId = rs.getInt("commentRefId");
		doBoughtDate = rs.getDate("boughtDate");
		doSoldDate = rs.getDate("soldDate");
		doBoughtPrice = rs.getDouble("boughtPrice");
		doSoldPrice = rs.getDouble("soldPrice");	
		// composes do in second step
		if (doCommentRefId != null) {
			CommentDO doComment = new CommentDO();
			doComment.setCommentRefId(doCommentRefId);
			item.setComment(doComment);
		}
		item.setItemRefId(doItemRefId);
		item.setName(doName);
		item.setDescription(doDescription);
		item.setBarCode(doBarCode);
		item.setSellerRefId(doSellerRefId);
		item.setBoughtDate(doBoughtDate);
		item.setSoldDate(doSoldDate);
		item.setBoughtPrice(doBoughtPrice);
		item.setSoldPrice(doSoldPrice);
		
		return item;// returns do
	}

	@Override
	protected String getTableName() {
		logger.trace("running getTableName...");
		return "item";
	}

	@Override
	protected String getInsertQuery() {
		logger.trace("running getInsertQuery...");
		return INSERT_ITEM_QUERY;
	}

	@Override
	protected String getUpdateQuery() {
		logger.trace("running getUpdateQuery...");
		return UPDATE_ITEM_QUERY;
	}

	@Override
	protected void setInsertParameters(PreparedStatement ps, ItemDO entity) throws SQLException {
		logger.trace("running setInsertParameters...");
		// Set fields
		setParameter(ps, 1, entity.getName(), java.sql.Types.VARCHAR); // name
        setParameter(ps, 2, entity.getDescription(),  java.sql.Types.VARCHAR); // description
        setParameter(ps, 3, entity.getBarCode(), java.sql.Types.INTEGER); // barCode
        setParameter(ps, 4, entity.getSellerRefId(), java.sql.Types.INTEGER); // sellerRefId
        if (entity.getComment() != null ) {
        	setParameter(ps, 5, entity.getComment().getCommentRefId(), java.sql.Types.INTEGER); // Set NULL if no commentRefId
        } else {
        	setParameter(ps, 5, null, java.sql.Types.INTEGER); // Set NULL if no commentRefId
        }
        setParameter(ps, 6, entity.getBoughtDate(), java.sql.Types.DATE); // Set NULL if no boughtDate
        setParameter(ps, 7, entity.getSoldDate(), java.sql.Types.DATE); // Set NULL if no soldDate
        setParameter(ps, 8, entity.getBoughtPrice(), java.sql.Types.DOUBLE); // boughtPrice
        setParameter(ps, 9, entity.getSoldPrice(), java.sql.Types.DOUBLE); // boughtPrice
	}

	@Override
	protected void setUpdateParameters(PreparedStatement ps, ItemDO entity) throws SQLException {
		logger.trace("running setUpdateParameters...");
		// Set fields
		setParameter(ps, 1, entity.getName(), java.sql.Types.VARCHAR); // name
        setParameter(ps, 2, entity.getDescription(),  java.sql.Types.VARCHAR); // description
        setParameter(ps, 3, entity.getBarCode(), java.sql.Types.INTEGER); // barCode
        setParameter(ps, 4, entity.getSellerRefId(), java.sql.Types.INTEGER); // sellerRefId
        if (entity.getComment() != null ) {
        	setParameter(ps, 5, entity.getComment().getCommentRefId(), java.sql.Types.INTEGER); // Set commentRefId
        } else {
        	setParameter(ps, 5, null, java.sql.Types.INTEGER); // Set NULL if no commentRefId
        }
        setParameter(ps, 6, entity.getBoughtDate(), java.sql.Types.DATE); // boughtDate
        setParameter(ps, 7, entity.getSoldDate(), java.sql.Types.DATE); // soldDate
        setParameter(ps, 8, entity.getBoughtPrice(), java.sql.Types.DOUBLE); // boughtPrice
        setParameter(ps, 9, entity.getSoldPrice(), java.sql.Types.DOUBLE); // boughtPrice
        setParameter(ps, 10, entity.getItemRefId(), java.sql.Types.INTEGER); // itemRefId
	}

	@Override
	protected String getRefIdName() {
		logger.trace("running getRefIdName...");
		return "itemRefId";
	}
}
