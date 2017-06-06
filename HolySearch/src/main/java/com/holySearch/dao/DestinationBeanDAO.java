package com.holySearch.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.holySearch.parser.DestinationParser;

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
	public void deleteAllDestinations() {
		List<Destination> liste = getAllDestinations();
		if (liste != null && !liste.isEmpty()) {
			for (Destination destination : liste) {
				entityManager.remove(destination);
			}
		}
		entityManager.close();
	}

	public void newDestinationInDatabase() throws Exception {

		ArrayList<Destination> liste = null;
		ArrayList<String> countryNameList = null;
		ArrayList<String> cityNameList = null;
		try {
			DestinationParser vDestinationParser = new DestinationParser();
			liste = vDestinationParser.getDestinations();
			countryNameList = vDestinationParser.getCountriesNameList();
			cleanList(liste, countryNameList);
			System.out.println("add Beaches");
			for (Destination beach : liste) {
				if (!beach.getDestinationEnglishName().toString().contains("?")
						&& !beach.getDestinationFrenchName().toString().contains("?")) {
					System.out.println("add Beach " + beach.toString());
					if (countryNameList.get(liste.indexOf(beach)) != null
							&& !countryNameList.get(liste.indexOf(beach)).isEmpty()
							&& !"null".equals(countryNameList.get(liste.indexOf(beach)))
							&& !countryNameList.get(liste.indexOf(beach)).toString().contains("?")) {
						beach.setCountry(
								getCountryBeanByFrenchOrEnglishName(countryNameList.get(liste.indexOf(beach))));
					}
					entityManager.persist(beach);
				}
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

	private void cleanList(ArrayList<Destination> destinationsList, ArrayList<String> countriesNameList) {
		for (int i = 0; i < destinationsList.size(); i++) {
			for (int j = i + 1; j < destinationsList.size(); j++) {
				if (destinationsList.get(i).getDestinationEnglishName()
						.equals(destinationsList.get(j).getDestinationEnglishName())) {
					System.out.println("supression de " + destinationsList.get(j).getDestinationEnglishName());
					destinationsList.remove(j);
					countriesNameList.remove(j);
					j--;
				}
			}
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Destination getDestinationBeanById(Integer id) {
		Destination vDestination = null;
		try {
			vDestination = (Destination) entityManager
					.createQuery("SELECT A FROM Destination A WHERE A.destinationId = :destinationid")
					.setParameter("destinationid", id).getSingleResult();
		} catch (NoResultException e) {
			log.trace("erreur sql de recherche de la destination : null");
		} catch (NonUniqueResultException e) {
			log.trace("erreur sql de recherche de la destination : plusieurs resultats");
		}
		entityManager.close();
		return vDestination;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Destination> getAllDestinations() {
		List<Destination> vDestination = null;
		try {
			vDestination = (List<Destination>) entityManager.createQuery("SELECT A FROM Destination A").getResultList();
		} catch (NoResultException e) {
			log.trace("erreur sql de récupération des destinations");
		}
		entityManager.close();
		return vDestination;
	}

}