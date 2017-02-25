package com.holySearch.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.dao.ContinentBeanDAO;

@Component
public class ContinentService {

	@Autowired
	ContinentBeanDAO mContinentBeanDAO;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insertContinents() throws Exception {
		mContinentBeanDAO.newContinentInDatabase();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteAllContinent() throws Exception {
		mContinentBeanDAO.deleteAllContinent();
	}
}