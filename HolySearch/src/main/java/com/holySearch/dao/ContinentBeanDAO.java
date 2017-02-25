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
	public List<Continent> getAllContinent() {
		List<Continent> vReturnContinent = null;
		Query vQuery = entityManager.createQuery("select u FROM Continent u");
		vReturnContinent = (Vector<Continent>) vQuery.getResultList();
		entityManager.close();
		return vReturnContinent;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteAllContinent() {
		List<Continent> continents = getAllContinent();
		if (continents != null && !continents.isEmpty()) {
			System.out.println("remove");
			for (Continent continent : continents) {
				entityManager.remove(continent);
			}
		}
		entityManager.close();
	}

	public void newContinentInDatabase() throws Exception {

		ArrayList<Continent> continentsList = null;
		try {

			continentsList = ContinentParser.getContinents();
			for (Continent continent : continentsList) {
				System.out.println(continent);
				entityManager.persist(continent);
			}
			entityManager.close();
		} catch (IOException e) {
			log.trace(e);
		}

	}

}