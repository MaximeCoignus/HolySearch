package com.holySearch.dao;

import java.io.UnsupportedEncodingException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.Continent;
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

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Continent getContinentBeanByFrenchName(String frenchName) {
		Continent vReturnContinent = null;
		Query vQuery = entityManager.createQuery("SELECT u FROM Continent u WHERE u.continentFrenchName = :name");
		vQuery.setParameter("name", frenchName);

		try {
			vReturnContinent = (Continent) vQuery.getSingleResult();
		} catch (NoResultException e) {
		}
		entityManager.close();
		return vReturnContinent;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Continent getContinentBeanByEnglishName(String englishName) {
		Continent vReturnContinent = null;
		Query vQuery = entityManager.createQuery("SELECT u FROM Continent u WHERE u.continentEnglishName = :name");
		vQuery.setParameter("name", englishName);

		try {
			vReturnContinent = (Continent) vQuery.getSingleResult();
		} catch (NoResultException e) {
		}
		entityManager.close();
		return vReturnContinent;
	}

}