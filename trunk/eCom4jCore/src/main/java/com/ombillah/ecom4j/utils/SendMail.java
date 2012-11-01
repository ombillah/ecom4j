package com.ombillah.ecom4j.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Utility class to send emails.
 * @author Oussama M Billah
 *
 */
public final class SendMail {

	/**
	 * making constructor private
	 * to restrict creating instance for static only members.
	 */
	private SendMail() {
		
	}
	
	public static void sendMail(String to, String from, String subject,
			String content) throws Exception {
		
		//TODO: move to properties files.
		String host = "s155.eatj.com";
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		SMTPAuthenticator auth = new SMTPAuthenticator();

		Session session = Session.getDefaultInstance(props, auth);

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));
		InternetAddress[] address = { new InternetAddress(to) };
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(content);

		Transport.send(msg);
	}
	
	/**
	 * inner class to handle SMTP authentication.
	 * @author Oussama M Billah
	 *
	 */
	private static class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("omb10",
					"xxxxxxxx");
		}
	}
}
