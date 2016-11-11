package com.holySearch.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.Beach;
import com.holySearch.bean.User;
import com.holySearch.controller.MainController;



@Repository
public class BeachBeanDAO {
	
	private static final Logger log = Logger.getLogger(BeachBeanDAO.class);


	@PersistenceContext
	transient EntityManager entityManager;

	public BeachBeanDAO() {
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Beach getBeachBeanByNom(String nom) {
		Beach vBeach = null;
		try{
		vBeach = (Beach) entityManager.createQuery("SELECT u FROM Beach u WHERE u.beachName = :name")
				.setParameter("name", nom).getResultList().get(0);
		entityManager.close();
		}catch (Exception e) {
			log.trace("erreur sql de recherche de la plage");
		}
		return vBeach;
	}
	
	
	
}
