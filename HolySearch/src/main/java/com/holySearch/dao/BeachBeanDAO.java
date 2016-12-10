package com.holySearch.dao;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.Beach;
import com.holySearch.parser.BeachParser;



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
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void newBeachInDatabase () {
		// Declaration de la variable url
		String url = null;
		ArrayList<Beach> beachesList = null;
		try {
			// Cette url prend comme valeur le lien du Webservice qui renvoie la liste des plages
			url = "http://overpass-api.de/api/interpreter?data=[out:json];area[name=%22France%22];"
			+ "(node[natural=%22beach%22](area););out;";
			// Appel de la methode getBeaches avec en parametre
			// l'url du webservice
			beachesList = BeachParser.getBeaches(url);
			// Verifie que la liste n'est pas vide
			if (beachesList != null && beachesList.size() != 0 && !beachesList.isEmpty()) {
				// Parcourt l'ensemble des plages recuperees
				for (int i = 0; i < beachesList.size(); i++) {
					// On insere les plages une par une dans la BDD
					Beach beach = beachesList.get(i);
					// Requete d'insertion
					entityManager.createQuery("INSERT INTO Beach (beach_name, latitude, longitude, address) "
							+ "VALUES "
							+ "('" + beach.getBeachName() + "', " + beach.getLatitude() 
							+ ", " + beach.getLongitude() + ", 'No Address')");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
