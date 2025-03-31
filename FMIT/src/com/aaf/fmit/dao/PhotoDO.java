package com.aaf.fmit.dao;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Chris McDermit
 */
public class PhotoDO {

	private Integer photoRefId;
	private byte[] photo;
	private CommentDO comment;

	public PhotoDO() {
	}

	/**
	 * Returns the photoRefId of Photo.
	 *
	 * @return the photoRefId
	 */
	public final Integer getPhotoRefId() {
		return photoRefId;
	}

	/**
	 * Sets the photoRefId to value passed in.
	 *
	 * @param photoRefId the photoRefId to set
	 */
	public final void setPhotoRefId(Integer photoRefId) {
		this.photoRefId = photoRefId;
	}

	/**
	 * Returns the photo of Photo.
	 *
	 * @return the photo
	 */
	public final byte[] getPhoto() {
		return photo;
	}

	/**
	 * Sets the photo to value passed in.
	 *
	 * @param photo the photo to set
	 */
	public final void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * Returns the comment of Photo.
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(photo);
		result = prime * result + Objects.hash(comment, photoRefId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PhotoDO))
			return false;
		PhotoDO other = (PhotoDO) obj;
		return Objects.equals(comment, other.comment) && Arrays.equals(photo, other.photo)
				&& Objects.equals(photoRefId, other.photoRefId);
	}

	@Override
	public String toString() {
		final int maxLen = 3;
		return "PhotoDO [" + (photoRefId != null ? "photoRefId=" + photoRefId + ", " : "")
				+ (photo != null
						? "photo=" + Arrays.toString(Arrays.copyOf(photo, Math.min(photo.length, maxLen))) + ", "
						: "")
				+ (comment != null ? "comment=" + comment : "") + "]";
	}
}
