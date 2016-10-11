package com.holySearch.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.PrimaryKey;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int userId;

	
	@Column(name = "nom")
	private String userNom;
	

	@Column(name = "prenom")
	private String userPrenom;
	
	@Column(name = "email")
	private String userEmail;
	

	@Column(name = "login")
	private String userLogin;
	
	@Column(name = "password")
	private String userPassword;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "birthday")
	private Date userBirthday;


	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}


	/**
	 * @return the userNom
	 */
	public String getUserNom() {
		return userNom;
	}


	/**
	 * @param userNom the userNom to set
	 */
	public void setUserNom(String userNom) {
		this.userNom = userNom;
	}


	/**
	 * @return the userPrenom
	 */
	public String getUserPrenom() {
		return userPrenom;
	}


	/**
	 * @param userPrenom the userPrenom to set
	 */
	public void setUserPrenom(String userPrenom) {
		this.userPrenom = userPrenom;
	}


	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}


	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	/**
	 * @return the userLogin
	 */
	public String getUserLogin() {
		return userLogin;
	}


	/**
	 * @param userLogin the userLogin to set
	 */
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}


	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}


	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	/**
	 * @return the userBirthday
	 */
	public Date getUserBirthday() {
		return userBirthday;
	}


	/**
	 * @param userBirthday the userBirthday to set
	 */
	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}
	
	

}
