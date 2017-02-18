package com.holySearch.services;

import java.io.UnsupportedEncodingException;

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
	public void insertContinents(String url) throws Exception {
		if (!url.isEmpty()) {
			mContinentBeanDAO.newContinentInDatabase(url);
		}
	}
}