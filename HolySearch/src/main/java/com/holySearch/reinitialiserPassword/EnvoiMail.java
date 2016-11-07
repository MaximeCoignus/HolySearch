package com.holySearch.reinitialiserPassword;

public class EnvoiMail {
	private String email;
	private String password;
	private String identifiant;
	
	
	
	public EnvoiMail(String email, String password, String identifiant) {
		this.email = email;
		this.password = password;
		this.identifiant = identifiant;
	}
	
	public EnvoiMail(String email, String password) {
		this.email = email;
		this.password = password;
	}



	public void sendMailReinitialisation() {
		SendMailSSL s = new SendMailSSL(email, "Réinitialisation du mot de passe de votre compte HolySearch", "Votre mot de passe sur HolySearch a été réinitialiser"
				+ "\n Nouveau mot de passe : " + password);
		s.sendMail();
	}
	
	public void sendMailCreationCompte() {
		SendMailSSL s = new SendMailSSL(email, "Creation de votre compte HolySearch", "Merci d'avoir créer un compte sur HolySearch"
				+ "\n Identifiant : " + identifiant
				+ "\n Mot de passe : " + password);
		s.sendMail();
	}
}
