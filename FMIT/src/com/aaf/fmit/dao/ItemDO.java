package com.aaf.fmit.dao;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Chris McDermit
 */
public class ItemDO {

	private Integer itemRefId;
	private String name;
	private String description;
	private Integer barCode;
	private Integer sellerRefId;
	private CommentDO comment;
	private Date boughtDate;
	private Date soldDate;
	private Double boughtPrice;
	private Double soldPrice;
    private List<PhotoDO> photoList;	
    private List<MarketDO> marketList;
	
	public ItemDO() {}

	/**
	 * Returns the itemRefId of ItemDO.
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

	/**
	 * Returns the name of ItemDO.
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
	 * Returns the description of ItemDO.
	 *
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * Sets the description to value passed in. 
	 *
	 * @param description the description to set
	 */
	public final void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the barCode of ItemDO.
	 *
	 * @return the barCode
	 */
	public final Integer getBarCode() {
		return barCode;
	}

	/**
	 * Sets the barCode to value passed in. 
	 *
	 * @param barCode the barCode to set
	 */
	public final void setBarCode(Integer barCode) {
		this.barCode = barCode;
	}

	/**
	 * Returns the sellerRefId of ItemDO.
	 *
	 * @return the sellerRefId
	 */
	public final Integer getSellerRefId() {
		return sellerRefId;
	}

	/**
	 * Sets the sellerRefId to value passed in. 
	 *
	 * @param sellerRefId the sellerRefId to set
	 */
	public final void setSellerRefId(Integer sellerRefId) {
		this.sellerRefId = sellerRefId;
	}

	/**
	 * Returns the comment of ItemDO.
	 *
	 * @return the comment
	 */
	public final CommentDO getComment() {
		return comment;
	}

	/**
	 * Sets the comment to value passed in. 
	 *
	 * @param comment the comment to set
	 */
	public final void setComment(CommentDO comment) {
		this.comment = comment;
	}

	/**
	 * Returns the boughtDate of ItemDO.
	 *
	 * @return the boughtDate
	 */
	public final Date getBoughtDate() {
		return boughtDate;
	}

	/**
	 * Sets the boughtDate to value passed in. 
	 *
	 * @param boughtDate the boughtDate to set
	 */
	public final void setBoughtDate(Date boughtDate) {
		this.boughtDate = boughtDate;
	}

	/**
	 * Returns the soldDate of ItemDO.
	 *
	 * @return the soldDate
	 */
	public final Date getSoldDate() {
		return soldDate;
	}

	/**
	 * Sets the soldDate to value passed in. 
	 *
	 * @param soldDate the soldDate to set
	 */
	public final void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
	}

	/**
	 * Returns the boughtPrice of ItemDO.
	 *
	 * @return the boughtPrice
	 */
	public final Double getBoughtPrice() {
		return boughtPrice;
	}

	/**
	 * Sets the boughtPrice to value passed in. 
	 *
	 * @param boughtPrice the boughtPrice to set
	 */
	public final void setBoughtPrice(Double boughtPrice) {
		this.boughtPrice = boughtPrice;
	}

	/**
	 * Returns the soldPrice of ItemDO.
	 *
	 * @return the soldPrice
	 */
	public final Double getSoldPrice() {
		return soldPrice;
	}

	/**
	 * Sets the soldPrice to value passed in. 
	 *
	 * @param soldPrice the soldPrice to set
	 */
	public final void setSoldPrice(Double soldPrice) {
		this.soldPrice = soldPrice;
	}

	/**
	 * Returns the photoList of ItemDO.
	 *
	 * @return the photoList
	 */
	public final List<PhotoDO> getPhotoList() {
		return photoList;
	}

	/**
	 * Sets the photoList to value passed in. 
	 *
	 * @param photoList the photoList to set
	 */
	public final void setPhotoList(List<PhotoDO> photoList) {
		this.photoList = photoList;
	}

	/**
	 * Returns the marketList of ItemDO.
	 *
	 * @return the marketList
	 */
	public final List<MarketDO> getMarketList() {
		return marketList;
	}

	/**
	 * Sets the marketList to value passed in. 
	 *
	 * @param marketList the marketList to set
	 */
	public final void setMarketList(List<MarketDO> marketList) {
		this.marketList = marketList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(barCode, boughtDate, boughtPrice, comment, description, itemRefId, marketList, name,
				photoList, sellerRefId, soldDate, soldPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ItemDO))
			return false;
		ItemDO other = (ItemDO) obj;
		return Objects.equals(barCode, other.barCode) && Objects.equals(boughtDate, other.boughtDate)
				&& Objects.equals(boughtPrice, other.boughtPrice) && Objects.equals(comment, other.comment)
				&& Objects.equals(description, other.description) && Objects.equals(itemRefId, other.itemRefId)
				&& Objects.equals(marketList, other.marketList) && Objects.equals(name, other.name)
				&& Objects.equals(photoList, other.photoList) && Objects.equals(sellerRefId, other.sellerRefId)
				&& Objects.equals(soldDate, other.soldDate) && Objects.equals(soldPrice, other.soldPrice);
	}

	@Override
	public String toString() {
		return "ItemDO [" + (itemRefId != null ? "itemRefId=" + itemRefId + ", " : "")
				+ (name != null ? "name=" + name + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (barCode != null ? "barCode=" + barCode + ", " : "")
				+ (sellerRefId != null ? "sellerRefId=" + sellerRefId + ", " : "")
				+ (comment != null ? "comment=" + comment + ", " : "")
				+ (boughtDate != null ? "boughtDate=" + boughtDate + ", " : "")
				+ (soldDate != null ? "soldDate=" + soldDate + ", " : "")
				+ (boughtPrice != null ? "boughtPrice=" + boughtPrice + ", " : "")
				+ (soldPrice != null ? "soldPrice=" + soldPrice : "") + "]";
	}
}
