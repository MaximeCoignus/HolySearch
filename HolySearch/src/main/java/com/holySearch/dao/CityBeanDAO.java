package com.holySearch.dao;

import java.io.UnsupportedEncodingException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.City;
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
		// Ajouter le pays
		entityManager.persist(vCity);
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public City getCityBeanByFrenchName(String frenchName) {
		City vReturnCity = null;
		Query vQuery = entityManager.createQuery("SELECT u FROM City u WHERE u.cityFrenchName = :name");
		vQuery.setParameter("name", frenchName);

		try {
			vReturnCity = (City) vQuery.getSingleResult();
		} catch (NoResultException e) {
		}
		entityManager.close();
		return vReturnCity;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public City getCityBeanByEnglishName(String englishName) {
		City vReturnCity = null;
		Query vQuery = entityManager.createQuery("SELECT u FROM City u WHERE u.cityEnglishName = :name");
		vQuery.setParameter("name", englishName);

		try {
			vReturnCity = (City) vQuery.getSingleResult();
		} catch (NoResultException e) {
		}
		entityManager.close();
		return vReturnCity;
	}

}