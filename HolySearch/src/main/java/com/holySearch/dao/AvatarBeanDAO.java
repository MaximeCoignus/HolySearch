package com.holySearch.dao;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.Avatar;

@Repository
public class AvatarBeanDAO {

	@PersistenceContext
	transient EntityManager entityManager;

	public AvatarBeanDAO() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Avatar getAvatarBeanByNom(String nom) {
		Avatar vReturnAvatar = null;
		Query requete = entityManager.createQuery("SELECT u FROM Avatar u WHERE u.avatarName = :name")
				.setParameter("name", nom);
		try {
			vReturnAvatar = (Avatar) requete.getSingleResult();
		} catch (NoResultException e) {
		}
		entityManager.close();
		return vReturnAvatar;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createNewAvatar(String nom, byte[] image) throws IOException {
		// TODO Auto-generated method stub
		Avatar vAvatar = new Avatar();
		vAvatar.setAvatarName(nom);
		vAvatar.setPicture(image);
		entityManager.persist(vAvatar);
		entityManager.close();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateAvatar(String nom, byte[] image) throws IOException {
		Avatar avatar = this.getAvatarBeanByNom(nom);
		if (avatar != null) {
			avatar.setPicture(image);
			entityManager.persist(avatar);
			entityManager.close();
		}else{
			this.createNewAvatar(nom, image);
		}

	}

}