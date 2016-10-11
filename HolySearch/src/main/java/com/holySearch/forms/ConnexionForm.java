package com.holySearch.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class ConnexionForm {
	@NotEmpty
	private String login;
	
	@NotEmpty
	private String password;

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
