package com.holySearch.dao;

import java.io.UnsupportedEncodingException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.Continent;

@Repository
public class ContinentBeanDAO {

	@PersistenceContext
	transient EntityManager entityManager;

	public ContinentBeanDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createNewContinent(String continentEnglishName, String continentFrenchName, float continentLongitude,
			float continentLatitude, float continentPopulation, float continentSizeKilometer,
			String continentWikiDescription, String continentWikiPicture) throws UnsupportedEncodingException {
		Continent vContinent = new Continent();
		vContinent.setContinentEnglishName(continentEnglishName);
		vContinent.setContinentFrenchName(continentFrenchName);
		vContinent.setContinentLongitude(continentLongitude);
		vContinent.setContinentLatitude(continentLatitude);
		vContinent.setContinentPopulation(continentPopulation);
		vContinent.setContinentSizeKilometer(continentSizeKilometer);
		vContinent.setContinentWikiDescription(continentWikiDescription);
		vContinent.setContinentWikiPicture(continentWikiPicture);
		entityManager.persist(vContinent);
		entityManager.close();
	}

}