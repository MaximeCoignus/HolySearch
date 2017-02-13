package com.holySearch.dao;

import java.io.UnsupportedEncodingException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.City;

@Repository
public class CityBeanDAO {

	@PersistenceContext
	transient EntityManager entityManager;

	public CityBeanDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createNewCity(String cityEnglishName, String cityFrenchName, float cityLongitude, float cityLatitude,
			float cityPopulation, String cityWikiDescription, String cityWikiPicture, boolean isCapital)
			throws UnsupportedEncodingException {
		City vCity = new City();
		vCity.setCityEnglishName(cityEnglishName);
		vCity.setCityFrenchName(cityFrenchName);
		vCity.setCityLongitude(cityLongitude);
		vCity.setCityLatitude(cityLatitude);
		vCity.setCityPopulation(cityPopulation);
		vCity.setCityWikiDescription(cityWikiDescription);
		vCity.setCityWikiPicture(cityWikiPicture);
		vCity.setCapital(isCapital);
		entityManager.persist(vCity);
		entityManager.close();
	}

}