package com.aaf.fmit.dao;

public class UserDO {
	private Integer userRefId;
	private String name;
	
	public UserDO() {}

	/**
	 * Constructs a User 
	 * @param userRefId
	 * @param name
	 */
	public UserDO(Integer userRefId, String name) {
		this.userRefId = userRefId;
		this.name = name;
	}

	/**
	 * Returns the userRefId of UserDO.
	 *
	 * @return the userRefId
	 */
	public final Integer getUserRefId() {
		return userRefId;
	}

	/**
	 * Sets the userRefId to value passed in. 
	 *
	 * @param userRefId the userRefId to set
	 */
	public final void setUserRefId(Integer userRefId) {
		this.userRefId = userRefId;
	}

	/**
	 * Returns the name of UserDO.
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

	@Override
	public String toString() {
		return "UserDO [" + (userRefId != null ? "userRefId=" + userRefId + ", " : "")
				+ (name != null ? "name=" + name : "") + "]";
	}	
	
	
}
