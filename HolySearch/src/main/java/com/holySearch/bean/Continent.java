package com.holySearch.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "continent")
public class Continent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "continentid")
	private int continentId;

	@Column(name = "englishname")
	private String continentEnglishName;

	@Column(name = "frenchname")
	private String continentFrenchName;

	@Column(name = "longitude")
	private float continentLongitude;

	@Column(name = "latitude")
	private float continentLatitude;

	@Column(name = "population")
	private float continentPopulation;

	@Column(name = "sizekm")
	private float continentSizeKilometer;

	@Column(name = "wikidescription")
	private String continentWikiDescription;

	@Column(name = "wikipicture")
	private String continentWikiPicture;

	/**
	 * @return the continentEnglishName
	 */
	public String getContinentEnglishName() {
		return continentEnglishName;
	}

	/**
	 * @param continentEnglishName
	 *            the continentEnglishName to set
	 */
	public void setContinentEnglishName(String continentEnglishName) {
		this.continentEnglishName = continentEnglishName;
	}

	/**
	 * @return the continentFrenchName
	 */
	public String getContinentFrenchName() {
		return continentFrenchName;
	}

	/**
	 * @param continentFrenchName
	 *            the continentFrenchName to set
	 */
	public void setContinentFrenchName(String continentFrenchName) {
		this.continentFrenchName = continentFrenchName;
	}

	/**
	 * @return the continentLongitude
	 */
	public float getContinentLongitude() {
		return continentLongitude;
	}

	/**
	 * @param continentLongitude
	 *            the continentLongitude to set
	 */
	public void setContinentLongitude(float continentLongitude) {
		this.continentLongitude = continentLongitude;
	}

	/**
	 * @return the continentLatitude
	 */
	public float getContinentLatitude() {
		return continentLatitude;
	}

	/**
	 * @param continentLatitude
	 *            the continentLatitude to set
	 */
	public void setContinentLatitude(float continentLatitude) {
		this.continentLatitude = continentLatitude;
	}

	/**
	 * @return the continentPopulation
	 */
	public float getContinentPopulation() {
		return continentPopulation;
	}

	/**
	 * @param continentPopulation
	 *            the continentPopulation to set
	 */
	public void setContinentPopulation(float continentPopulation) {
		this.continentPopulation = continentPopulation;
	}

	/**
	 * @return the continentSizeKilometer
	 */
	public float getContinentSizeKilometer() {
		return continentSizeKilometer;
	}

	/**
	 * @param continentSizeKilometer
	 *            the continentSizeKilometer to set
	 */
	public void setContinentSizeKilometer(float continentSizeKilometer) {
		this.continentSizeKilometer = continentSizeKilometer;
	}

	/**
	 * @return the continentWikiDescription
	 */
	public String getContinentWikiDescription() {
		return continentWikiDescription;
	}

	/**
	 * @param continentWikiDescription
	 *            the continentWikiDescription to set
	 */
	public void setContinentWikiDescription(String continentWikiDescription) {
		this.continentWikiDescription = continentWikiDescription;
	}

	/**
	 * @return the continentWikiPicture
	 */
	public String getContinentWikiPicture() {
		return continentWikiPicture;
	}

	/**
	 * @param continentWikiPicture
	 *            the continentWikiPicture to set
	 */
	public void setContinentWikiPicture(String continentWikiPicture) {
		this.continentWikiPicture = continentWikiPicture;
	}

	/**
	 * @return the continentId
	 */
	public int getContinentId() {
		return continentId;
	}

}
