package com.holySearch.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "avatars")
public class Avatar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int avatarId;

	@Column(name = "name")
	private String avatarName;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "image")
	private byte[] picture;

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
	 * @return the avatarId
	 */
	public int getAvatarId() {
		return avatarId;
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
