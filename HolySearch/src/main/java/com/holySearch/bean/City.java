package com.holySearch.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cityid")
	private int cityId;

	@Column(name = "englishname")
	private String cityEnglishName;

	@Column(name = "frenchname")
	private String cityFrenchName;

	@Column(name = "longitude")
	private float cityLongitude;

	@Column(name = "latitude")
	private float cityLatitude;

	@Column(name = "population")
	private float cityPopulation;

	@Column(name = "wikidescription")
	private String cityWikiDescription;

	@Column(name = "wikipicture")
	private String cityWikiPicture;

	@Column(name = "isCapital")
	private boolean isCapital;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "countryid", referencedColumnName = "countryid")
	private Country country;

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
	 * @return
	 */
	public int getCityId() {
		return this.cityId;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

}
