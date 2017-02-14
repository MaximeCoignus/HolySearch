package com.holySearch.transfert.object;

public class CityTO {

	private int cityId;

	private String cityEnglishName;

	private String cityFrenchName;

	private float cityLongitude;

	private float cityLatitude;

	private float cityPopulation;

	private String cityWikiDescription;

	private String cityWikiPicture;

	private boolean isCapital;

	/**
	 * @return the cityEnglishName
	 */
	public String getCityEnglishName() {
		return cityEnglishName;
	}

	/**
	 * @param cityEnglishName
	 *            the cityEnglishName to set
	 */
	public void setCityEnglishName(String cityEnglishName) {
		this.cityEnglishName = cityEnglishName;
	}

	/**
	 * @return the cityFrenchName
	 */
	public String getCityFrenchName() {
		return cityFrenchName;
	}

	/**
	 * @param cityFrenchName
	 *            the cityFrenchName to set
	 */
	public void setCityFrenchName(String cityFrenchName) {
		this.cityFrenchName = cityFrenchName;
	}

	/**
	 * @return the cityLongitude
	 */
	public float getCityLongitude() {
		return cityLongitude;
	}

	/**
	 * @param cityLongitude
	 *            the cityLongitude to set
	 */
	public void setCityLongitude(float cityLongitude) {
		this.cityLongitude = cityLongitude;
	}

	/**
	 * @return the cityLatitude
	 */
	public float getCityLatitude() {
		return cityLatitude;
	}

	/**
	 * @param cityLatitude
	 *            the cityLatitude to set
	 */
	public void setCityLatitude(float cityLatitude) {
		this.cityLatitude = cityLatitude;
	}

	/**
	 * @return the cityPopulation
	 */
	public float getCityPopulation() {
		return cityPopulation;
	}

	/**
	 * @param cityPopulation
	 *            the cityPopulation to set
	 */
	public void setCityPopulation(float cityPopulation) {
		this.cityPopulation = cityPopulation;
	}

	/**
	 * @return the cityWikiDescription
	 */
	public String getCityWikiDescription() {
		return cityWikiDescription;
	}

	/**
	 * @param cityWikiDescription
	 *            the cityWikiDescription to set
	 */
	public void setCityWikiDescription(String cityWikiDescription) {
		this.cityWikiDescription = cityWikiDescription;
	}

	/**
	 * @return the cityWikiPicture
	 */
	public String getCityWikiPicture() {
		return cityWikiPicture;
	}

	/**
	 * @param cityWikiPicture
	 *            the cityWikiPicture to set
	 */
	public void setCityWikiPicture(String cityWikiPicture) {
		this.cityWikiPicture = cityWikiPicture;
	}

	/**
	 * @return the isCapital
	 */
	public boolean isCapital() {
		return isCapital;
	}

	/**
	 * @param isCapital
	 *            the isCapital to set
	 */
	public void setCapital(boolean isCapital) {
		this.isCapital = isCapital;
	}

	/**
	 * @param cityId
	 *            the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}

}
