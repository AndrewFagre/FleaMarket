/**
 * @author Chris McDermit
 */
package com.aaf.fmit.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class that houses business logic which depends on data service layer
 * 
 * @author Chris McDermit
 */
public class Repository {
	private static final Logger logger = LogManager.getLogger(Repository.class);
	

	/**
	 * Constructs a Repository 
	 */
	public Repository() {
		logger.trace("Creating a new Repository.");
	}
	
	//TODO add/edit user (detail - add only when no refid)
	//TODO get all users (listing)
	//TODO add/edit market (detail - add only when no refid)
	//TODO get all markets - no items (listing & item detail)
	//TODO get market with all items 
	//TODO add/edit new item (detail - add only when no refid)
	//TODO find items by user
	//TODO find items by market
	//TODO more report queries
}
