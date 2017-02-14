package com.holySearch.forms;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("unused")
public class AvatarForm {

	private MultipartFile userAvatarFile;

	/**
	 * @return the userAvatarFile
	 */
	public MultipartFile getUserAvatarFile() {
		return userAvatarFile;
	}

	/**
	 * @param userAvatarFile
	 *            the userAvatarFile to set
	 */
	public void setUserAvatarFile(MultipartFile userAvatarFile) {
		this.userAvatarFile = userAvatarFile;
	}

}
