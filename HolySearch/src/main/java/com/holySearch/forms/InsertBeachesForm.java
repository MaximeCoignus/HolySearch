package com.holySearch.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class InsertBeachesForm {

	@NotEmpty
	private String url;

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
	
	
}
