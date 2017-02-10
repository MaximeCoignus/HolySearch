package com.holySearch.transfert.object;

public class AvatarTO {
	private int avatarId;
	private String avatarName;
	private byte[] picture;

	/**
	 * @return the avatarId
	 */
	public int getAvatarId() {
		return avatarId;
	}

	/**
	 * @param avatarId
	 *            the avatarId to set
	 */
	public void setAvatarId(int avatarId) {
		this.avatarId = avatarId;
	}

	/**
	 * @return the avatarName
	 */
	public String getAvatarName() {
		return avatarName;
	}

	/**
	 * @param avatarName
	 *            the avatarName to set
	 */
	public void setAvatarName(String avatarName) {
		this.avatarName = avatarName;
	}

	/**
	 * @return the picture
	 */
	public byte[] getPicture() {
		return picture;
	}

	/**
	 * @param picture
	 *            the picture to set
	 */
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

}
