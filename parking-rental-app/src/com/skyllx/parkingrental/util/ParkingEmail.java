package com.skyllx.parkingrental.util;

	import java.util.Properties;
	import javax.mail.Authenticator;
	import javax.mail.Message;
	import javax.mail.MessagingException;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeMessage;

	import org.springframework.stereotype.Component;

	@Component
	public class ParkingEmail {
		
		private String host;
		private String user;
		private String pswd;
		private String to;
		private String port;
		
		public boolean sendMail(String email, String name) {
			 host = "smtp.office365.com";
			 user = "info@skyllx.com";
			 pswd = "skyllx@123";
			 to = email;
			 port="587";

			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			props.put("mail.transport.protocal", "smtp");

			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(user, pswd);
				}
			});

			try {
				MimeMessage msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(user));
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			//	msg.setReplyTo(new InternetAddress[] {new InternetAddress("Savitri.Kandgal@outlook.com"), new InternetAddress(to)});
				msg.setSubject("Login confirmation mail");
				msg.setContent("<H3>Hello " + name + ",</H3>"
						+ "You have logged in successfully!"
						+ "<br/> "
						+ "<br/> "
						+ "<br/> "
						+ "<b>Thanks & Regards</b>,<br/>"
						+ "X-workz", "text/html");
				Transport.send(msg);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			return true;
		}
	}

