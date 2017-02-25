package com.holySearch.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.dao.CountryBeanDAO;

@Component
public class CountryService {

	@Autowired
	CountryBeanDAO mCountryBeanDAO;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insertCountries() throws Exception {
		mCountryBeanDAO.newCountryInDatabase();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteAllCountry() throws Exception {
		mCountryBeanDAO.deleteAllCountry();
	}
}