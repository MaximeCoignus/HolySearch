package com.holySearch.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "countryid")
	private int countryId;
	
	@Column(name = "englishname")
	private String countryEnglishName;
	
	@Column(name = "frenchname")
	private String countryFrenchName;
	
	@Column(name = "longitude")
	private float countryLongitude;
	
	@Column(name = "latitude")
	private float countryLatitude;
	
	@Column(name = "population")
	private float countryPopulation;
	
	@Column(name = "currency")
	private String countryCurrency;
	
	@Column(name = "isoa2")
	private String countryIsoA2;
	
	@Column(name = "isoa3")
	private String countryIsoA3;
	
	@Column(name = "wikidescription")
	private String countryWikiDescription;
	
	@Column(name = "wikipicture")
	private String countryWikiPicture;
	
	@Column(name = "temperature")
	private float countryTemperature;
	
	@Column(name = "temperaturelevel")
	private String countryTemperatureLevel;
	
	@Column(name = "precipitation")
	private float countryPrecipitation;
	
	@Column(name = "precipitationlevel")
	private String countryPrecipitationLevel;
	
	@Column(name = "criminality")
	private float countryCriminality;
	
	@Column(name = "criminalityLevel")
	private String countryCriminalityLevel;

	/**
	 * @return the countryEnglishName
	 */
	public String getCountryEnglishName() {
		return countryEnglishName;
	}

	/**
	 * @param countryEnglishName the countryEnglishName to set
	 */
	public void setCountryEnglishName(String countryEnglishName) {
		this.countryEnglishName = countryEnglishName;
	}

	/**
	 * @return the countryFrenchName
	 */
	public String getCountryFrenchName() {
		return countryFrenchName;
	}

	/**
	 * @param countryFrenchName the countryFrenchName to set
	 */
	public void setCountryFrenchName(String countryFrenchName) {
		this.countryFrenchName = countryFrenchName;
	}

	/**
	 * @return the countryLongitude
	 */
	public float getCountryLongitude() {
		return countryLongitude;
	}

	/**
	 * @param countryLongitude the countryLongitude to set
	 */
	public void setCountryLongitude(float countryLongitude) {
		this.countryLongitude = countryLongitude;
	}

	/**
	 * @return the countryLatitude
	 */
	public float getCountryLatitude() {
		return countryLatitude;
	}

	/**
	 * @param countryLatitude the countryLatitude to set
	 */
	public void setCountryLatitude(float countryLatitude) {
		this.countryLatitude = countryLatitude;
	}

	/**
	 * @return the countryPopulation
	 */
	public float getCountryPopulation() {
		return countryPopulation;
	}

	/**
	 * @param countryPopulation the countryPopulation to set
	 */
	public void setCountryPopulation(float countryPopulation) {
		this.countryPopulation = countryPopulation;
	}

	/**
	 * @return the countryCurrency
	 */
	public String getCountryCurrency() {
		return countryCurrency;
	}

	/**
	 * @param countryCurrency the countryCurrency to set
	 */
	public void setCountryCurrency(String countryCurrency) {
		this.countryCurrency = countryCurrency;
	}

	/**
	 * @return the countryIsoA2
	 */
	public String getCountryIsoA2() {
		return countryIsoA2;
	}

	/**
	 * @param countryIsoA2 the countryIsoA2 to set
	 */
	public void setCountryIsoA2(String countryIsoA2) {
		this.countryIsoA2 = countryIsoA2;
	}

	/**
	 * @return the countryIsoA3
	 */
	public String getCountryIsoA3() {
		return countryIsoA3;
	}

	/**
	 * @param countryIsoA3 the countryIsoA3 to set
	 */
	public void setCountryIsoA3(String countryIsoA3) {
		this.countryIsoA3 = countryIsoA3;
	}

	/**
	 * @return the countryWikiDescription
	 */
	public String getCountryWikiDescription() {
		return countryWikiDescription;
	}

	/**
	 * @param countryWikiDescription the countryWikiDescription to set
	 */
	public void setCountryWikiDescription(String countryWikiDescription) {
		this.countryWikiDescription = countryWikiDescription;
	}

	/**
	 * @return the countryWikiPicture
	 */
	public String getCountryWikiPicture() {
		return countryWikiPicture;
	}

	/**
	 * @param countryWikiPicture the countryWikiPicture to set
	 */
	public void setCountryWikiPicture(String countryWikiPicture) {
		this.countryWikiPicture = countryWikiPicture;
	}

	/**
	 * @return the countryTemperature
	 */
	public float getCountryTemperature() {
		return countryTemperature;
	}

	/**
	 * @param countryTemperature the countryTemperature to set
	 */
	public void setCountryTemperature(float countryTemperature) {
		this.countryTemperature = countryTemperature;
	}

	/**
	 * @return the countryTemperatureLevel
	 */
	public String getCountryTemperatureLevel() {
		return countryTemperatureLevel;
	}

	/**
	 * @param countryTemperatureLevel the countryTemperatureLevel to set
	 */
	public void setCountryTemperatureLevel(String countryTemperatureLevel) {
		this.countryTemperatureLevel = countryTemperatureLevel;
	}

	/**
	 * @return the countryPrecipitation
	 */
	public float getCountryPrecipitation() {
		return countryPrecipitation;
	}

	/**
	 * @param countryPrecipitation the countryPrecipitation to set
	 */
	public void setCountryPrecipitation(float countryPrecipitation) {
		this.countryPrecipitation = countryPrecipitation;
	}

	/**
	 * @return the countryPrecipitationLevel
	 */
	public String getCountryPrecipitationLevel() {
		return countryPrecipitationLevel;
	}

	/**
	 * @param countryPrecipitationLevel the countryPrecipitationLevel to set
	 */
	public void setCountryPrecipitationLevel(String countryPrecipitationLevel) {
		this.countryPrecipitationLevel = countryPrecipitationLevel;
	}

	/**
	 * @return the countryCriminality
	 */
	public float getCountryCriminality() {
		return countryCriminality;
	}

	/**
	 * @param countryCriminality the countryCriminality to set
	 */
	public void setCountryCriminality(float countryCriminality) {
		this.countryCriminality = countryCriminality;
	}

	/**
	 * @return the countryCriminalityLevel
	 */
	public String getCountryCriminalityLevel() {
		return countryCriminalityLevel;
	}

	/**
	 * @param countryCriminalityLevel the countryCriminalityLevel to set
	 */
	public void setCountryCriminalityLevel(String countryCriminalityLevel) {
		this.countryCriminalityLevel = countryCriminalityLevel;
	}

	/**
	 * @return the countryId
	 */
	public int getCountryId() {
		return countryId;
	}
	
	
}
