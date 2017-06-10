package com.holySearch.transfert.object;

public class DestinationItemTO {

	private int destinationId;

	private String destinationEnglishName;

	private String destinationFrenchName;

	private float destinationLongitude;

	private float destinationLatitude;

	private String destinationType;

	private CountryItemTO country;

	/**
	 * @return the destinationId
	 */
	public int getDestinationId() {
		return destinationId;
	}

	/**
	 * @param destinationId
	 *            the destinationId to set
	 */
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}

	/**
	 * @return the destinationEnglishName
	 */
	public String getDestinationEnglishName() {
		return destinationEnglishName;
	}

	/**
	 * @param destinationEnglishName
	 *            the destinationEnglishName to set
	 */
	public void setDestinationEnglishName(String destinationEnglishName) {
		this.destinationEnglishName = destinationEnglishName;
	}

	/**
	 * @return the destinationFrenchName
	 */
	public String getDestinationFrenchName() {
		return destinationFrenchName;
	}

	/**
	 * @param destinationFrenchName
	 *            the destinationFrenchName to set
	 */
	public void setDestinationFrenchName(String destinationFrenchName) {
		this.destinationFrenchName = destinationFrenchName;
	}

	/**
	 * @return the destinationLongitude
	 */
	public float getDestinationLongitude() {
		return destinationLongitude;
	}

	/**
	 * @param destinationLongitude
	 *            the destinationLongitude to set
	 */
	public void setDestinationLongitude(float destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}

	/**
	 * @return the destinationLatitude
	 */
	public float getDestinationLatitude() {
		return destinationLatitude;
	}

	/**
	 * @param destinationLatitude
	 *            the destinationLatitude to set
	 */
	public void setDestinationLatitude(float destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
	}

	/**
	 * @return the destinationType
	 */
	public String getDestinationType() {
		return destinationType;
	}

	/**
	 * @param destinationType
	 *            the destinationType to set
	 */
	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}

	/**
	 * @return the country
	 */
	public CountryItemTO getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(CountryItemTO country) {
		this.country = country;
	}

}
