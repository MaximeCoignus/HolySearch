package com.holySearch.dao;

import java.io.UnsupportedEncodingException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.Destination;
import com.holySearch.bean.Destination;

@Repository
public class DestinationBeanDAO {

	@PersistenceContext
	transient EntityManager entityManager;

	public DestinationBeanDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createNewDestination(String destinationEnglishName, String destinationFrenchName,
			float destinationLongitude, float destinationLatitude, String destinationWikiDescription,
			String destinationWikiPicture, String destinationType) throws UnsupportedEncodingException {
		Destination vDestination = new Destination();
		vDestination.setDestinationEnglishName(destinationEnglishName);
		vDestination.setDestinationFrenchName(destinationFrenchName);
		vDestination.setDestinationLatitude(destinationLatitude);
		vDestination.setDestinationLongitude(destinationLongitude);
		vDestination.setDestinationWikiDescription(destinationWikiDescription);
		vDestination.setDestinationWikiPicture(destinationWikiPicture);
		vDestination.setDestinationType(destinationType);
		// Ajouter la ville
		// Ajouter le pays
		entityManager.persist(vDestination);
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Destination getDestinationBeanByFrenchName(String frenchName) {
		Destination vReturnDestination = null;
		Query vQuery = entityManager.createQuery("SELECT u FROM Destination u WHERE u.destinationFrenchName = :name");
		vQuery.setParameter("name", frenchName);

		try {
			vReturnDestination = (Destination) vQuery.getSingleResult();
		} catch (NoResultException e) {
		}
		entityManager.close();
		return vReturnDestination;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Destination getDestinationBeanByEnglishName(String englishName) {
		Destination vReturnDestination = null;
		Query vQuery = entityManager.createQuery("SELECT u FROM Destination u WHERE u.destinationEnglishName = :name");
		vQuery.setParameter("name", englishName);

		try {
			vReturnDestination = (Destination) vQuery.getSingleResult();
		} catch (NoResultException e) {
		}
		entityManager.close();
		return vReturnDestination;
	}
}
