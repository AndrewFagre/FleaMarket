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
public class CommentDAO extends AbstractGenericDAO<CommentDO, Integer> {
	
	private static final Logger logger = LogManager.getLogger(CommentDAO.class);

	@Override
	protected CommentDO mapRow(ResultSet rs) throws SQLException {
		logger.trace("running mapRow...");
		CommentDO comment = new CommentDO();
		comment.setCommentRefId(rs.getInt("commentRefId"));
		comment.setComment(rs.getString("comment"));
		return comment;
	}

	@Override
	protected String getTableName() {
		logger.trace("running getTableName...");
		return "comment";
	}

	@Override
	protected String getInsertQuery() {
		logger.trace("running getInsertQuery...");
		return "INSERT INTO comment (comment) VALUES (?)";
	}

	@Override
	protected String getUpdateQuery() {
		logger.trace("running getUpdateQuery...");
		return "UPDATE comment SET comment = ? WHERE commentRefId = ?";
	}

	@Override
	protected void setInsertParameters(PreparedStatement ps, CommentDO entity) throws SQLException {
		logger.trace("running setInsertParameters...");
		ps.setString(1, entity.getComment());
	}

	@Override
	protected void setUpdateParameters(PreparedStatement ps, CommentDO entity) throws SQLException {
		logger.trace("running setUpdateParameters...");
		ps.setString(1, entity.getComment());
		ps.setInt(2, entity.getCommentRefId());
	}

	@Override
	protected String getRefIdName() {
		logger.trace("running getRefIdName...");
		return "commentRefId";
	}
}
