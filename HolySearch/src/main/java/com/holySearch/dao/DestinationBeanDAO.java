package com.holySearch.dao;

import java.io.UnsupportedEncodingException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.Destination;

@Repository
public class DestinationBeanDAO {

	@PersistenceContext
	transient EntityManager entityManager;
	
	public DestinationBeanDAO() {
		// TODO Auto-generated constructor stub
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createNewDestination(String destinationEnglishName, String destinationFrenchName, float destinationLongitude,
			float destinationLatitude, String destinationWikiDescription, String destinationWikiPicture,
			String destinationType) throws UnsupportedEncodingException {
		Destination vDestination = new Destination();
		vDestination.setDestinationEnglishName(destinationEnglishName);
		vDestination.setDestinationFrenchName(destinationFrenchName);
		vDestination.setDestinationLatitude(destinationLatitude);
		vDestination.setDestinationLongitude(destinationLongitude);
		vDestination.setDestinationWikiDescription(destinationWikiDescription);
		vDestination.setDestinationWikiPicture(destinationWikiPicture);
		vDestination.setDestinationType(destinationType);
		entityManager.persist(vDestination);
		entityManager.close();
	}
}
