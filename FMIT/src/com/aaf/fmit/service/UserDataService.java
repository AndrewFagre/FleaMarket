package com.aaf.fmit.service;

import com.aaf.fmit.dao.CommentDAO;
import com.aaf.fmit.dao.CommentDO;
import com.aaf.fmit.dao.UserDAO;
import com.aaf.fmit.dao.UserDO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * The service layer for a user.
 * 
 * @author Chris McDermit
 */
public class UserDataService extends GenericServiceImpl<UserDO, Integer>  {

	private static final Logger logger = LogManager.getLogger(UserDataService.class);
	
	private final CommentDAO commentDAO;

	
	public UserDataService(UserDAO dao, CommentDAO cDao) {
		super(dao);
		logger.trace("Creating a new user data service.");
		this.commentDAO = cDao;
	}
	
	/**
	 * Makes one atomic transaction for persisting both the comment and the photo.
	 * 
	 * @param user
	 * @return
	 */
	public int persist(UserDO user) {
		int rowsAffected = NOPOS;

		// save comment first in the event the comment has a new refid to persist with the photo.
		if (user.getComment() != null) {
			if (user.getComment().getCommentRefId() != null) {
				rowsAffected = commentDAO.update(user.getComment());
			} else {
				rowsAffected = commentDAO.create(user.getComment());
				user.getComment().setCommentRefId(rowsAffected);
			}
		}

		// now save the photo with the potentially new commentRefId
		if (user.getUserRefId() != null) {
			rowsAffected = super.update(user.getUserRefId(), user);
		} else {
			rowsAffected = super.create(user);
		}
		return rowsAffected;
	}
	
	
	public UserDO retrieve(Integer id) {
		UserDO returnObject = super.retrieve(id);
		if (returnObject.getComment() != null && returnObject.getComment().getCommentRefId() != null) {
			CommentDO commentDO = commentDAO.retrieve(returnObject.getComment().getCommentRefId());
			returnObject.setComment(commentDO);
		}
		return returnObject;
	}
}
