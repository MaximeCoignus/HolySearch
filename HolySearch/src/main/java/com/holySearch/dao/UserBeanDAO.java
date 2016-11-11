package com.holySearch.dao;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.User;

@Repository
public class UserBeanDAO {

	@PersistenceContext
	transient EntityManager entityManager;

	public UserBeanDAO() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked") 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User getUserBeanByNom(String nom) {
		User user = (User) entityManager.createQuery("SELECT u FROM User u WHERE u.userNom = :name")
				.setParameter("name", nom).getResultList().get(0);
		entityManager.close();
		return user;
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createNewUser(String nom, String prenom, String email, String login, String password, Date birthday) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		User vUser = new User();
		vUser.setUserNom(nom);
		vUser.setUserPrenom(prenom);
		vUser.setUserEmail(email);
		vUser.setUserLogin(login);
		vUser.setUserPassword(MySQLPassword(password));
		vUser.setUserBirthday(birthday);
		entityManager.persist(vUser);
		entityManager.close();
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User getUserBeanByNomAndPassword(String login, String password) throws UnsupportedEncodingException {

		User vReturnUser = null;
		password = MySQLPassword(password);
		Query vQuery = entityManager
				.createQuery("SELECT A FROM User A WHERE A.userLogin= :login AND A.userPassword= :password");
		vQuery.setParameter("login", login);
		vQuery.setParameter("password", password);

		try {
			vReturnUser = (User) vQuery.getSingleResult();
		} catch (NoResultException e) {
		}
		entityManager.close();
		return vReturnUser;

	}

	public static String MySQLPassword(String plainText) throws UnsupportedEncodingException {
		byte[] utf8 = plainText.getBytes("UTF-8");
		return "*" + DigestUtils.shaHex(DigestUtils.sha(utf8)).toUpperCase();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User modifyUserPassword(String email, String password) throws UnsupportedEncodingException {
		User user = null;
		password = MySQLPassword(password);
		Query vQuery = entityManager
				.createQuery("SELECT A FROM User A WHERE A.userEmail= :email");
		vQuery.setParameter("email", email);
		try {
			user = (User) vQuery.getSingleResult();
		} catch (NoResultException e) {
		}
		user.setUserPassword(password);
		entityManager.merge(user);
		entityManager.close();
		return user;
	}

}