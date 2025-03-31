/**
 * @author Chris McDermit
 */
package com.aaf.fmit.service;

import com.aaf.fmit.dao.MarketDAO;
import com.aaf.fmit.dao.MarketDO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The service layer for a market.
 * 
 * @author Chris McDermit
 */
public class MarketDataService extends GenericServiceImpl<MarketDO, Integer>{

	private static final Logger logger = LogManager.getLogger(MarketDataService.class);
	
	public MarketDataService(MarketDAO dao) {
		super(dao);
		logger.trace("Creating a new market data service.");
	}
}
