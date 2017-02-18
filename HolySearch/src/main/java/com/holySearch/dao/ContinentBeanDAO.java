package com.holySearch.dao;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.Continent;
import com.holySearch.parser.ContinentParser;

@Repository
public class ContinentBeanDAO {

	private static final Logger log = Logger.getLogger(ContinentBeanDAO.class);

	@PersistenceContext
	transient EntityManager entityManager;

	public ContinentBeanDAO() {
		// TODO Auto-generated constructor stub
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

	public void newContinentInDatabase(String url) throws Exception {

		ArrayList<Continent> continentsList = null;
		try {
			// Appel de la methode getBeaches avec en parametre
			// l'url du webservice
			continentsList = ContinentParser.getContinents(url);
			for (Continent continent : continentsList) {
				System.out.println(continent);
				if (getContinentBeanByFrenchName(continent.getContinentFrenchName()) == null
						&& getContinentBeanByEnglishName(continent.getContinentEnglishName()) == null) {
					entityManager.persist(continent);
				}
			}
			entityManager.close();
		} catch (IOException e) {
			log.trace(e);
		}

	}

}