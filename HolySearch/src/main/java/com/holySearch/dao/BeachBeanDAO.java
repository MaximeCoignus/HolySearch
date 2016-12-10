package com.holySearch.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.Beach;



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
		vBeach = (Beach) entityManager.createQuery("SELECT A FROM Beach A WHERE A.beachName = :name")
				.setParameter("name", nom).getSingleResult();
		}catch (NoResultException e) {
			log.trace("erreur sql de recherche de la plage");
		}catch (NonUniqueResultException e) {
			log.trace("erreur sql de recherche de la plage");
		}
		entityManager.close();
		return vBeach;
	}
}
