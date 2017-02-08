package com.holySearch.reinitialiserPassword;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendMailSSL {
	String email, sujet, message;
	
	public SendMailSSL(String email, String sujet, String message){
		this.email = email;
		this.sujet = sujet;
		this.message = message;
	}
	
	public String sendMail(){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("holysearch.contact@gmail.com","Holy123456");
				}
			}); 
 
		try {
 
			Message msge = new MimeMessage(session);
			msge.setFrom(new InternetAddress("holysearch.contact@gmail.com"));
			msge.setRecipients(Message.RecipientType.TO, 
					InternetAddress.parse(email));
			msge.setSubject(sujet);
			msge.setText(message);
 
			Transport.send(msge);
 
			return "Votre message a été bien envoyé";
 
		} catch (MessagingException e) {
			return "Votre message n'a pas pu être envoyé";
		}
	}
}