/**
 * @author Chris McDermit
 */
package com.aaf.fmit.service;

import com.aaf.fmit.dao.CommentDAO;
import com.aaf.fmit.dao.CommentDO;
import com.aaf.fmit.dao.PhotoDAO;
import com.aaf.fmit.dao.PhotoDO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The service layer for a photo.
 * 
 * @author Chris McDermit
 */
public class PhotoDataService extends GenericServiceImpl<PhotoDO, Integer> {

	private static final Logger logger = LogManager.getLogger(PhotoDataService.class);

	private final CommentDAO commentDAO;

	/**
	 * 
	 * Constructs a PhotoDataService
	 * 
	 * @param dao  the photo dao
	 * @param cDao the comment dao
	 */
	public PhotoDataService(PhotoDAO dao, CommentDAO cDao) {
		super(dao);
		logger.trace("Creating a new photo data service.");
		this.commentDAO = cDao;
	}

	/**
	 * Makes one atomic transaction for persisting both the comment and the photo.
	 * 
	 * @param photo
	 * @return
	 */
	public int persist(PhotoDO photo) {
		int rowsAffected = NOPOS;

		// save comment first in the event the comment has a new refid to persist with the photo.
		if (photo.getComment() != null) {
			if (photo.getComment().getCommentRefId() != null) {
				rowsAffected = commentDAO.update(photo.getComment());
			} else {
				rowsAffected = commentDAO.create(photo.getComment());
				photo.getComment().setCommentRefId(rowsAffected);
			}
		}

		// now save the photo with the potentially new commentRefId
		if (photo.getPhotoRefId() != null) {
			rowsAffected = super.update(photo.getPhotoRefId(), photo);
		} else {
			rowsAffected = super.create(photo);
		}
		return rowsAffected;
	}
	
	
	public PhotoDO retrieve(Integer id) {
		PhotoDO returnObject = super.retrieve(id);
		if (returnObject.getComment() != null && returnObject.getComment().getCommentRefId() != null) {
			CommentDO commentDO = commentDAO.retrieve(returnObject.getComment().getCommentRefId());
			returnObject.setComment(commentDO);
		}
		return returnObject;
	}
}
