package com.holySearch.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.Beach;
import com.holySearch.bean.User;



@Repository
public class BeachBeanDAO {

	@PersistenceContext
	transient EntityManager entityManager;

	public BeachBeanDAO() {
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Beach getBeachBeanByNom(String nom) {
		Beach vBeach = (Beach) entityManager.createQuery("SELECT u FROM Beach u WHERE u.beachName = :name")
				.setParameter("name", nom).getResultList().get(0);
		entityManager.close();
		return vBeach;
	}
	
	
	
}
