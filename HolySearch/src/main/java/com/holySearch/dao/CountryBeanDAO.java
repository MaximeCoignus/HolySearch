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

import com.holySearch.bean.Continent;
import com.holySearch.bean.Country;
import com.holySearch.parser.CountryParser;

@Repository
public class CountryBeanDAO {

	private static final Logger log = Logger.getLogger(CountryBeanDAO.class);

	@PersistenceContext
	transient EntityManager entityManager;

	public CountryBeanDAO() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Country> getAllCountry() {
		List<Country> vReturnCountry = null;
		Query vQuery = entityManager.createQuery("SELECT u FROM Country u");
		vReturnCountry = (Vector<Country>) vQuery.getResultList();
		entityManager.close();
		return vReturnCountry;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteAllCountry() {
		List<Country> countries = getAllCountry();
		if (countries != null && !countries.isEmpty()) {
			System.out.println("remove");
			for (Country country : countries) {
				entityManager.remove(country);
			}
		}
		entityManager.close();

	}

	public void newCountryInDatabase() throws Exception {

		ArrayList<Country> countriesList = null;
		ArrayList<String> continentNameList = null;
		try {
			// Appel de la methode getBeaches avec en parametre
			// l'url du webservice
			countriesList = CountryParser.getCountries();
			continentNameList = CountryParser.getContinentNameList();
			cleanList(countriesList, continentNameList);
			for (Country country : countriesList) {
				if (!country.getCountryEnglishName().toString().contains("?")
						&& !country.getCountryFrenchName().toString().contains("?")) {
					System.out.println(country);
					if (continentNameList.get(countriesList.indexOf(country)) != null
							&& !continentNameList.get(countriesList.indexOf(country)).isEmpty()
							&& !"null".equals(continentNameList.get(countriesList.indexOf(country)))) {
						System.out.println("continent Name = " + continentNameList.get(countriesList.indexOf(country)));
						country.setContinent(
								getContinentBeanByEnglishName(continentNameList.get(countriesList.indexOf(country))));
					}
					entityManager.persist(country);
				}

			}
			entityManager.close();
		} catch (IOException e) {
			log.trace(e);
		}

	}

	private void cleanList(ArrayList<Country> countriesList, ArrayList<String> continentNameList) {
		for (int i = 0; i < countriesList.size(); i++) {
			for (int j = i + 1; j < countriesList.size(); j++) {
				if (countriesList.get(i).getCountryEnglishName().equals(countriesList.get(j).getCountryEnglishName())) {
					System.out.println("supression de " + countriesList.get(j).getCountryEnglishName());
					countriesList.remove(j);
					continentNameList.remove(j);
					j--;
				}
			}
		}
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
