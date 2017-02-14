package com.holySearch.transfert.object;

import java.io.File;
import java.util.Date;

public class UserBeanTO {

	private String userNom;

	private String userPrenom;

	private String userEmail;

	private String userLogin;

	private String userPassword;

	private Date userBirthday;

	/**
	 * @return the userNom
	 */
	public String getUserNom() {
		return userNom;
	}

	/**
	 * @param userNom
	 *            the userNom to set
	 */
	public void setUserNom(String userNom) {
		this.userNom = userNom;
	}

	/**
	 * @return the userPrenom
	 */
	public String getUserPrenom() {
		return userPrenom;
	}

	/**
	 * @param userPrenom
	 *            the userPrenom to set
	 */
	public void setUserPrenom(String userPrenom) {
		this.userPrenom = userPrenom;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail
	 *            the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the userLogin
	 */
	public String getUserLogin() {
		return userLogin;
	}

	/**
	 * @param userLogin
	 *            the userLogin to set
	 */
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword
	 *            the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the userBirthday
	 */
	public Date getUserBirthday() {
		return userBirthday;
	}

	/**
	 * @param userBirthday
	 *            the userBirthday to set
	 */
	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}
}