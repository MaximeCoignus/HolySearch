package com.holySearch.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class InsertDataForm {

	@NotEmpty
	private String url;
	
	@NotEmpty
	private String objet;

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the objet
	 */
	public String getObjet() {
		return objet;
	}

	/**
	 * @param objet the objet to set
	 */
	public void setObjet(String objet) {
		this.objet = objet;
	}
	
	
}
