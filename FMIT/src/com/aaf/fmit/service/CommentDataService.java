/**
 * @author Chris McDermit
 */
package com.aaf.fmit.service;

import com.aaf.fmit.dao.CommentDAO;
import com.aaf.fmit.dao.CommentDO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The service layer for a comment. 
 * 
 * @author Chris McDermit
 */
public class CommentDataService extends GenericServiceImpl<CommentDO, Integer> {

	private static final Logger logger = LogManager.getLogger(CommentDataService.class);

	public CommentDataService(CommentDAO dao) {
		super(dao);
		logger.trace("Creating a new comment data service.");
	}
}
