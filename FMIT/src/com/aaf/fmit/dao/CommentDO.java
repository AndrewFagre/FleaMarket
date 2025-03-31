package com.aaf.fmit.dao;

import java.util.Objects;

/**
 * @author Chris McDermit
 */
public class CommentDO {
	
	private Integer commentRefId;
	private String comment;
	
	public CommentDO() {}

	/**
	 * Returns the commentRefId of CommentDO.
	 *
	 * @return the commentRefId
	 */
	public final Integer getCommentRefId() {
		return commentRefId;
	}

	/**
	 * Sets the commentRefId to value passed in. 
	 *
	 * @param commentRefId the commentRefId to set
	 */
	public final void setCommentRefId(Integer commentRefId) {
		this.commentRefId = commentRefId;
	}

	/**
	 * Returns the comment of CommentDO.
	 *
	 * @return the comment
	 */
	public final String getComment() {
		return comment;
	}

	/**
	 * Sets the comment to value passed in. 
	 *
	 * @param comment the comment to set
	 */
	public final void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(comment, commentRefId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CommentDO))
			return false;
		CommentDO other = (CommentDO) obj;
		return Objects.equals(comment, other.comment) && Objects.equals(commentRefId, other.commentRefId);
	}

	@Override
	public String toString() {
		return "CommentDO [" + (commentRefId != null ? "commentRefId=" + commentRefId + ", " : "")
				+ (comment != null ? "comment=" + comment : "") + "]";
	}
}
