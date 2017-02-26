package com.holySearch.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.City;
import com.holySearch.bean.Country;
import com.holySearch.bean.Destination;
import com.holySearch.parser.BeachParser;

@Repository
public class DestinationBeanDAO {

	private static final Logger log = Logger.getLogger(DestinationBeanDAO.class);

	@PersistenceContext
	transient EntityManager entityManager;

	public DestinationBeanDAO() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Destination> getAllBeach() {
		List<Destination> vReturnBeach = null;
		Query vQuery = entityManager.createQuery("SELECT u FROM Destination u where u.destinationType = :type");
		vQuery.setParameter("type", "beach");
		vReturnBeach = (Vector<Destination>) vQuery.getResultList();
		entityManager.close();
		return vReturnBeach;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteAllBeach() {
		List<Destination> beachesList = getAllBeach();
		if (beachesList != null && !beachesList.isEmpty()) {
			for (Destination beach : beachesList) {
				entityManager.remove(beach);
			}
		}
		entityManager.close();
	}

	public void newBeachInDatabase() throws Exception {

		ArrayList<Destination> beachesList = null;
		ArrayList<String> countryNameList = null;
		ArrayList<String> cityNameList = null;
		try {
			BeachParser vBeachParser = new BeachParser();
			beachesList = vBeachParser.getBeaches();
			countryNameList = vBeachParser.getCountriesNameList();
			cityNameList = vBeachParser.getCitiesNameList();
			cleanList(beachesList, countryNameList, cityNameList);
			System.out.println("add Beaches");
			for (Destination beach : beachesList) {
				System.out.println("add Beaches " + beachesList.indexOf(beach));
				if (countryNameList.get(beachesList.indexOf(beach)) != null
						&& !countryNameList.get(beachesList.indexOf(beach)).isEmpty()
						&& !"null".equals(countryNameList.get(beachesList.indexOf(beach)))
						&& !countryNameList.get(beachesList.indexOf(beach)).toString().contains("?")) {
					beach.setCountry(
							getCountryBeanByFrenchOrEnglishName(countryNameList.get(beachesList.indexOf(beach))));
				}
				if (cityNameList.get(beachesList.indexOf(beach)) != null
						&& !cityNameList.get(beachesList.indexOf(beach)).isEmpty()
						&& !"null".equals(cityNameList.get(beachesList.indexOf(beach)))
						&& !countryNameList.get(beachesList.indexOf(beach)).toString().contains("?")) {
					beach.setCity(getCityBeanByFrenchOrEnglishName(cityNameList.get(beachesList.indexOf(beach))));
				}
				entityManager.persist(beach);
			}
			entityManager.close();
		} catch (IOException e) {
			log.trace(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Country getCountryBeanByFrenchOrEnglishName(String name) {
		Country vReturnCountry = null;
		Query vQuery = entityManager.createQuery(
				"SELECT u FROM Country u WHERE u.countryEnglishName = :name or u.countryFrenchName = :name");
		vQuery.setParameter("name", name);

		try {
			vReturnCountry = (Country) vQuery.getSingleResult();
		} catch (NoResultException e) {
		}
		entityManager.close();
		return vReturnCountry;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public City getCityBeanByFrenchOrEnglishName(String name) {
		City vReturnCity = null;
		Query vQuery = entityManager
				.createQuery("SELECT u FROM City u WHERE u.cityEnglishName = :name or u.cityFrenchName = :name");
		vQuery.setParameter("name", name);

		try {
			vReturnCity = (City) vQuery.getSingleResult();
		} catch (NoResultException e) {
		}
		entityManager.close();
		return vReturnCity;
	}

	private void cleanList(ArrayList<Destination> beachesList, ArrayList<String> countriesNameList,
			ArrayList<String> cityNameList) {
		for (int i = 0; i < beachesList.size(); i++) {
			for (int j = i + 1; j < beachesList.size(); j++) {
				if (beachesList.get(i).getDestinationEnglishName()
						.equals(beachesList.get(j).getDestinationEnglishName())) {
					System.out.println("supression de " + beachesList.get(j).getDestinationEnglishName());
					beachesList.remove(j);
					countriesNameList.remove(j);
					cityNameList.remove(j);
					j--;
				}
			}
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Destination getDestinationBeanByNom(String nom) {
		Destination vDestination = null;
		try {
			vDestination = (Destination) entityManager
					.createQuery("SELECT A FROM Destination A WHERE A.destinationFrenchName = :name")
					.setParameter("name", nom).getSingleResult();
		} catch (NoResultException e) {
			log.trace("erreur sql de recherche des destinations");
		} catch (NonUniqueResultException e) {
			log.trace("erreur sql de recherche de la destinations");
		}
		entityManager.close();
		return vDestination;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Destination> getAllDestinations() {
		List<Destination> vDestination = null;
		try {
			vDestination = (List<Destination>) entityManager.createQuery("SELECT A FROM Destination A").getResultList();
		} catch (NoResultException e) {
			log.trace("erreur sql de recherche de la plage");
		}
		entityManager.close();
		return vDestination;
	}

}