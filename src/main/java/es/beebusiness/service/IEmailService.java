package es.beebusiness.service;

import java.io.Serializable;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface IEmailService extends Serializable{

	public void enviarCredenciales(String destinatario, String password, String observaciones) throws AddressException, MessagingException;
}
