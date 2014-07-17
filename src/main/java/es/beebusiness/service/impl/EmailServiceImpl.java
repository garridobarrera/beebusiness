package es.beebusiness.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import es.beebusiness.service.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {

	private String host="smtp.gmail.com";
	private String port="465";
	private String user="beebusiness2014";
	private String password="2014beebusiness";
	private String from="beebusiness2014@gmail.com";
	private static final long serialVersionUID = -7887346274983618480L;

	@Override
	public void enviarCredenciales(String destinatario, String passwordNueva,
			String observaciones) throws AddressException, MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", port);
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user,password);
				}
			});
 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destinatario));
			message.setSubject("Nuevas credenciales de acceso");
			Multipart multipart = new MimeMultipart();
			// creates body part for the message
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("Gracias "+destinatario+", su nueva credencial de acceso es: <b>"+passwordNueva+"</b>", "text/html");
			// adds parts to the multipart
			multipart.addBodyPart(messageBodyPart);
			// sets the multipart as message's content
			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		IEmailService service=new EmailServiceImpl();
		try {
			service.enviarCredenciales("garridobarrera@gmail.com", "holita", "");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
