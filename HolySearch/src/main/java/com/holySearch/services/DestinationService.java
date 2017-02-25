package com.holySearch.services;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.Destination;
import com.holySearch.dao.DestinationBeanDAO;

@Component
public class DestinationService {

	@Autowired
	DestinationBeanDAO mDestinationBeanDAO;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insertBeaches() throws Exception {
		mDestinationBeanDAO.newBeachInDatabase();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteAllBeaches() throws Exception {
		mDestinationBeanDAO.deleteAllBeach();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Destination> getAllDestinations() throws Exception {
		return mDestinationBeanDAO.getAllDestinations();
	}

	/*
	 * Get Destination By nom
	 */
	public Destination getDestinationByNom(String nom) throws UnsupportedEncodingException {
		if (nom != null) {
			return mDestinationBeanDAO.getDestinationBeanByNom(nom);
		}
		return null;
	}
}
