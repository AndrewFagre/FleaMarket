package com.aaf.fmit.dao;

import java.util.List;
import java.util.Objects;

/**
 * @author Chris McDermit
 */
public class MarketDO {

	private Integer marketRefId;
	private String name;
	private List<ItemDO> items;
	
	public MarketDO() {}

	/**
	 * Returns the marketRefId of MarketDO.
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
	 * Returns the name of MarketDO.
	 *
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Sets the name to value passed in. 
	 *
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the items of MarketDO.
	 *
	 * @return the items
	 */
	public final List<ItemDO> getItems() {
		return items;
	}

	/**
	 * Sets the items to value passed in. 
	 *
	 * @param items the items to set
	 */
	public final void setItems(List<ItemDO> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(marketRefId, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof MarketDO))
			return false;
		MarketDO other = (MarketDO) obj;
		return Objects.equals(marketRefId, other.marketRefId) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "MarketDO [" + (marketRefId != null ? "marketRefId=" + marketRefId + ", " : "")
				+ (name != null ? "name=" + name : "") + "]";
	}
}
