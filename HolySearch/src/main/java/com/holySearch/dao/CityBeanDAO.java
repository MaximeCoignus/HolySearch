package com.holySearch.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.City;
import com.holySearch.bean.Country;
import com.holySearch.parser.CityParser;

@Repository
public class CityBeanDAO {

	private static final Logger log = Logger.getLogger(CityBeanDAO.class);

	@PersistenceContext
	transient EntityManager entityManager;

	public CityBeanDAO() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<City> getAllCity() {
		List<City> vReturnCity = null;
		Query vQuery = entityManager.createQuery("SELECT u FROM City u");
		vReturnCity = (Vector<City>) vQuery.getResultList();
		entityManager.close();
		return vReturnCity;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteAllCity() {
		List<City> cities = getAllCity();
		if (cities != null && !cities.isEmpty()) {
			for (City city : cities) {
				entityManager.remove(city);
			}
		}
		entityManager.close();
	}

	public void newCityInDatabase() throws Exception {

		ArrayList<City> citiesList = null;
		ArrayList<String> countryNameList = null;
		try {
			// Appel de la methode getBeaches avec en parametre
			// l'url du webservice
			citiesList = CityParser.getCities();
			countryNameList = CityParser.getCountryNameList();
			cleanList(citiesList, countryNameList);
			System.out.println("add cities");
			for (City city : citiesList) {
				if (!city.getCityEnglishName().toString().contains("?")
						&& !city.getCityFrenchName().toString().contains("?")) {
					System.out.println("add cities " + citiesList.indexOf(city));
					if (countryNameList.get(citiesList.indexOf(city)) != null
							&& !countryNameList.get(citiesList.indexOf(city)).isEmpty()
							&& !"null".equals(countryNameList.get(citiesList.indexOf(city)))) {
						city.setCountry(getCountryBeanByFrenchName(countryNameList.get(citiesList.indexOf(city))));
					}
					entityManager.persist(city);
				}
			}
			entityManager.close();
		} catch (IOException e) {
			log.trace(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Country getCountryBeanByFrenchName(String frenchName) {
		Country vReturnCountry = null;
		Query vQuery = entityManager.createQuery("SELECT u FROM Country u WHERE u.countryFrenchName = :name");
		vQuery.setParameter("name", frenchName);

		try {
			vReturnCountry = (Country) vQuery.getSingleResult();
		} catch (NoResultException e) {
		}
		entityManager.close();
		return vReturnCountry;
	}

	private void cleanList(ArrayList<City> citiesList, ArrayList<String> countriesNameList) {
		for (int i = 0; i < citiesList.size(); i++) {
			for (int j = i + 1; j < citiesList.size(); j++) {
				if (citiesList.get(i).getCityEnglishName().equals(citiesList.get(j).getCityEnglishName())) {
					System.out.println("supression de " + citiesList.get(j).getCityEnglishName());
					citiesList.remove(j);
					countriesNameList.remove(j);
					j--;
				}
			}
		}
	}

}