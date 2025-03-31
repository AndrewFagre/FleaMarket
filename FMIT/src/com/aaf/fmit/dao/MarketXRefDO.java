/**
 * @author Chris McDermit
 */
package com.aaf.fmit.dao;

/**
 * @author Chris McDermit
 */
public class MarketXRefDO {

	private Integer marketXRefId;
	private Integer marketRefId;
	private Integer itemRefId;

	/**
	 * Constructs a MarketXRefDO 
	 */
	public MarketXRefDO() {
	}

	/**
	 * Returns the marketXRefId of MarketXRefDO.
	 *
	 * @return the marketXRefId
	 */
	public final Integer getMarketXRefId() {
		return marketXRefId;
	}

	/**
	 * Sets the marketXRefId to value passed in. 
	 *
	 * @param marketXRefId the marketXRefId to set
	 */
	public final void setMarketXRefId(Integer marketXRefId) {
		this.marketXRefId = marketXRefId;
	}

	/**
	 * Returns the marketRefId of MarketXRefDO.
	 *
	 * @return the marketRefId
	 */
	public final Integer getMarketRefId() {
		return marketRefId;
	}

	/**
	 * Sets the marketRefId to value passed in. 
	 *
	 * @param marketRefId the marketRefId to set
	 */
	public final void setMarketRefId(Integer marketRefId) {
		this.marketRefId = marketRefId;
	}

	/**
	 * Returns the itemRefId of MarketXRefDO.
	 *
	 * @return the itemRefId
	 */
	public final Integer getItemRefId() {
		return itemRefId;
	}

	/**
	 * Sets the itemRefId to value passed in. 
	 *
	 * @param itemRefId the itemRefId to set
	 */
	public final void setItemRefId(Integer itemRefId) {
		this.itemRefId = itemRefId;
	}
}
