package com.holySearch.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;



@Repository
public class BeachBeanDAO {

	@PersistenceContext
	transient EntityManager entityManager;

	public BeachBeanDAO() {
		
	}
	
	
	
}
