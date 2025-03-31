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
public class PhotoDAO extends AbstractGenericDAO<PhotoDO, Integer> {
	private static final Logger logger = LogManager.getLogger(PhotoDAO.class);
	private static final int MAX_ALLOWED_SIZE = 16777215; //64kb
	private static final String ITEM_PHOTO_QUERY = "SELECT * FROM photo WHERE itemRefId = ?";
	private static final String FIND_ITEM_PHOTO_ERROR_MSG = "An Error occured attempting to find item market lists.";

	@Override
	protected PhotoDO mapRow(ResultSet rs) throws SQLException {
		logger.trace("running mapRow...");
		PhotoDO photo = new PhotoDO();
		photo.setPhotoRefId(rs.getInt("photoRefId"));
		byte[] photoBytes = rs.getBytes("photo");
		photo.setPhoto(photoBytes);
		CommentDO comment = new CommentDO();     
		comment.setCommentRefId(rs.getInt("commentRefId"));
		photo.setComment(comment);
		return photo;
	}

	@Override
	protected String getTableName() {
		logger.trace("running getTableName...");
		return "photo";
	}

	@Override
	protected String getInsertQuery() {
		logger.trace("running getInsertQuery...");
		return "INSERT INTO `photo` (`photo`, `itemRefId`, `commentRefId`) VALUES (?, ?, ?);";
	}

	@Override
	protected String getUpdateQuery() {
		logger.trace("running getUpdateQuery...");
//		return "UPDATE `photo` SET `photo` = ?, `itemRefId` = ?, `commentRefId` = ? WHERE `photoRefId` = ?;";
		return "UPDATE `photo` SET `photo` = ?, `commentRefId` = ? WHERE `photoRefId` = ?;";
	}

	@Override
	protected void setInsertParameters(PreparedStatement ps, PhotoDO entity) throws SQLException {
		logger.trace("running setInsertParameters...");
		// TODO set itemRefId
		
		//verify the photo is not excessively large
		byte[] photoBytes = entity.getPhoto(); // Photo data
		if (photoBytes.length > MAX_ALLOWED_SIZE) {
			throw new RuntimeException("Photo exceeds maximum allowed size.");
		}
		ps.setBytes(1, photoBytes);
		ps.setInt(2, 0);
		if (entity.getComment() != null) {
			ps.setInt(3, entity.getComment().getCommentRefId());
		} else {
			ps.setNull(3, java.sql.Types.INTEGER);
		}
	}

	@Override
	protected void setUpdateParameters(PreparedStatement ps, PhotoDO entity) throws SQLException {
		logger.trace("running setUpdateParameters...");
		byte[] photoBytes = entity.getPhoto(); // Photo data
		if (photoBytes.length > MAX_ALLOWED_SIZE) {
		    throw new RuntimeException("Photo exceeds maximum allowed size.");
		}
		ps.setBytes(1, photoBytes);
		ps.setInt(2, entity.getComment().getCommentRefId());
		ps.setInt(3, entity.getPhotoRefId());
	}

	@Override
	protected String getRefIdName() {
		logger.trace("running getRefIdName...");
		return "photoRefId";
	}
	
	/**
	 * Gets the photo list for the item ref passed in.
	 * 
	 * @param itemRefId the reference for the item
	 * @return the associated photos
	 */
	public List<PhotoDO> getItemPhotoList(int itemRefId) {
		logger.trace("running getItemPhotoList...");

		List<PhotoDO> returnList = new ArrayList<>();;
		
		try (Connection connection = ConnectionManager.get();
				PreparedStatement ps = connection.prepareStatement(ITEM_PHOTO_QUERY)) {
				ps.setInt(1, itemRefId);	
			try (ResultSet rs = ps.executeQuery()) { // using try blocks to free resources reliable
				while (rs.next()) {
					returnList.add(mapRow(rs)); // Process each row
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(FIND_ITEM_PHOTO_ERROR_MSG, e);
		}
		return returnList;
	}
}
