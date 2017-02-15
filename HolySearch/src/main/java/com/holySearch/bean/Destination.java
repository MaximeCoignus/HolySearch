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
@Table(name = "destination")
public class Destination {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "destinationid")
	private int destinationId;

	@Column(name = "englishname")
	private String destinationEnglishName;

	@Column(name = "frenchname")
	private String destinationFrenchName;

	@Column(name = "longitude")
	private float destinationLongitude;

	@Column(name = "latitude")
	private float destinationLatitude;

	@Column(name = "wikidescription")
	private String destinationWikiDescription;

	@Column(name = "wikipicture")
	private String destinationWikiPicture;

	@Column(name = "type")
	private String destinationType;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "countryid", referencedColumnName = "countryid")
	private Country country;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cityid", referencedColumnName = "cityid")
	private City city;

	/**
	 * @return the destinationId
	 */
	public int getDestinationId() {
		return destinationId;
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
	 * @return the destinationWikiDescription
	 */
	public String getDestinationWikiDescription() {
		return destinationWikiDescription;
	}

	/**
	 * @param destinationWikiDescription
	 *            the destinationWikiDescription to set
	 */
	public void setDestinationWikiDescription(String destinationWikiDescription) {
		this.destinationWikiDescription = destinationWikiDescription;
	}

	/**
	 * @return the destinationWikiPicture
	 */
	public String getDestinationWikiPicture() {
		return destinationWikiPicture;
	}

	/**
	 * @param destinationWikiPicture
	 *            the destinationWikiPicture to set
	 */
	public void setDestinationWikiPicture(String destinationWikiPicture) {
		this.destinationWikiPicture = destinationWikiPicture;
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

	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}

}
