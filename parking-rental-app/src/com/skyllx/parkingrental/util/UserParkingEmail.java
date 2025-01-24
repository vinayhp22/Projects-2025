package com.skyllx.parkingrental.util;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.skyllx.parkingrental.dto.UserParkingDTO;
import org.springframework.stereotype.Component;

@Component
public class UserParkingEmail {

	private String host;
	private String user;
	private String pswd;
	private String to;
	private String port;
	
	public boolean sendMail(String email, String name, UserParkingDTO upDto) {
		 host = "smtp.office365.com";
		 user = "Savitri.Kandgal@outlook.com";
		 pswd = "Sav3@123";
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
			msg.setSubject("SignUp Confirmation");
			msg.setContent("<H3>Hello " + name + ", </h3>"
					+ "<br/> "
					+ "<h5>You are registered successfully!</h5>"
					+ "Below is your parking details:<br/>"
					+ "Vehicle No.: "+upDto.getVehicleNo()+"<br/>"
					+ "Location: "+upDto.getLocation()+"<br/>"
					+ "Vehicle Type: "+upDto.getVehicleType()+"<br/>"
					+ "Engine Type: "+upDto.getEngineType()+"<br/>"
					+ "Classification: "+upDto.getClassification()+"<br/>"
					+ "Term: "+upDto.getTerm()+"<br/>"
					+ "Price: "+upDto.getPrice()+"<br/>"
					+ "Discount: "+upDto.getDiscount()+"<br/>"
					+ "Total Amount: "+upDto.getTotalAmount()
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
