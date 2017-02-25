package com.holySearch.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.dao.CityBeanDAO;

@Component
public class CityService {

	@Autowired
	CityBeanDAO mCityBeanDAO;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insertCities() throws Exception {
		mCityBeanDAO.newCityInDatabase();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteAllCity() throws Exception {
		mCityBeanDAO.deleteAllCity();
	}
}