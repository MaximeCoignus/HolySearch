package com.holySearch.reinitialiserPassword;

public class EnvoiMail {
	private String email;
	private String password;
	private String identifiant;
	private String objet;
	private String nom;
	private String prenom;
	private String message;
	
	
	
	public EnvoiMail(String email, String password, String identifiant) {
		this.email = email;
		this.password = password;
		this.identifiant = identifiant;
	}
	
	public EnvoiMail(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public EnvoiMail(String nom, String prenom, String email, String objet, String message) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.objet = objet;
		this.message = message;
	}



	public void sendMailReinitialisation() {
		SendMailSSL s = new SendMailSSL(email, "R�initialisation du mot de passe de votre compte HolySearch", "Votre mot de passe sur HolySearch a �t� r�initialiser"
				+ "\n Nouveau mot de passe : " + password);
		s.sendMail();
	}
	
	public void sendMailCreationCompte() {
		SendMailSSL s = new SendMailSSL(email, "Creation de votre compte HolySearch", "Merci d'avoir cr�er un compte sur HolySearch"
				+ "\n Identifiant : " + identifiant
				+ "\n Mot de passe : " + password);
		s.sendMail();
	}
	
	public void sendMailContactezNous() {
		SendMailSSL s1 = new SendMailSSL("holysearch.contact@gmail.com", "Message de HolySearch : Contactez-nous", "Nom : "+ this.nom + "\n\nPr�nom : "+ this.prenom + "\n\nEmail : "+ this.email + "\n\nSujet : "+ this.objet + "\n\nMessage : \n"+ this.message);
		s1.sendMail();
		SendMailSSL s2 = new SendMailSSL(email, "Votre message a bien �t� re�u par HolySearch", "Bonjour, \nHolySearch accuse r�cepton de votre message qui sera bient�t trait�. \nCordialement");
		s2.sendMail();
	}
}
