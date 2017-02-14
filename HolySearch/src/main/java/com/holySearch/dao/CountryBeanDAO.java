package com.holySearch.dao;

import java.io.UnsupportedEncodingException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.Country;

@Repository
public class CountryBeanDAO {

	@PersistenceContext
	transient EntityManager entityManager;
	
	public CountryBeanDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createNewCountry(String countryEnglishName, String countryFrenchName, float countryLongitude,
			float countryLatitude, float countryPopulation, String countryCurrency, String countryIsoA2,
			String countryIsoA3, String countryWikiDescription, String countryWikiPicture, float countryTemperature,
			String countryTemperatureLevel, float countryPrecipitation, String countryPrecipitationLevel,
			float countryCriminality, String countryCriminalityLevel) throws UnsupportedEncodingException {
		Country vCountry = new Country();
		vCountry.setCountryEnglishName(countryEnglishName);
		vCountry.setCountryFrenchName(countryFrenchName);
		vCountry.setCountryLongitude(countryLongitude);
		vCountry.setCountryLatitude(countryLatitude);
		vCountry.setCountryPopulation(countryPopulation);
		vCountry.setCountryCurrency(countryCurrency);
		vCountry.setCountryIsoA2(countryIsoA2);
		vCountry.setCountryIsoA3(countryIsoA3);
		vCountry.setCountryWikiDescription(countryWikiDescription);
		vCountry.setCountryWikiPicture(countryWikiPicture);
		vCountry.setCountryTemperature(countryTemperature);
		vCountry.setCountryTemperatureLevel(countryTemperatureLevel);
		vCountry.setCountryPrecipitation(countryPrecipitation);
		vCountry.setCountryPrecipitationLevel(countryPrecipitationLevel);
		vCountry.setCountryCriminality(countryCriminality);
		vCountry.setCountryCriminalityLevel(countryCriminalityLevel);
	}
}
