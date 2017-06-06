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
	public void insertDestinations() throws Exception {
		mDestinationBeanDAO.newDestinationInDatabase();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteAllBeaches() throws Exception {
		mDestinationBeanDAO.deleteAllDestinations();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Destination> getAllDestinations() throws Exception {
		return mDestinationBeanDAO.getAllDestinations();
	}
	
	/*
	 * Get Destination By Id
	 */
	public Destination getDestinationById(Integer id) throws UnsupportedEncodingException {
		if (id != null) {
			return mDestinationBeanDAO.getDestinationBeanById(id);
		}
		return null;
	}
}
