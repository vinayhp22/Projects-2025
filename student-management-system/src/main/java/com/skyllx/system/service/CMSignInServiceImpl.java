package com.skyllx.system.service;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skyllx.system.dto.UserDTO;
import com.skyllx.system.entity.UserEntity;
import com.skyllx.system.repository.CMSignInRepo;
import com.skyllx.system.repository.CMSignUpRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CMSignInServiceImpl implements CMSignInService {

	@Autowired
	private CMSignInRepo repo;

	@Autowired
	private CMSignUpRepo signUpRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	int attemptCount = 0;

	@Override
	public boolean authenticateUser(String userId, String password) {
		log.info("authenticateUser(String userId, String password) userId:" + userId + " Password:  " + password);
		UserEntity user = this.repo.findByUserId(userId);

		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			return true;
		}
		return false;
	}

	@Override
	public UserDTO findByUserId(String userId) {
		UserEntity entity = repo.findByUserId(userId);
		if (entity != null) {
			UserDTO dto = new UserDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		return CMSignInService.super.findByUserId(userId);
	}
	
	@Override
	public UserDTO findById(int id) {
		log.info("findById "+id);
		UserEntity entity = this.repo.findById(id);
		if (entity != null) {
			UserDTO dto = new UserDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		return CMSignInService.super.findById(id);
	}

	@Override
	public void updateAttempts(String userId, int attempts) {
		repo.updateAttempts(userId, attempts);
	}

	@Override
	public void updateLock(String userId) {
		repo.updateLock(userId);
	}

	/* Password Reset */
	@Override
	public boolean checkEmail(String email) {
		// checking the mail whether present or not
		Long countByEmail = this.signUpRepo.countByEmail(email);
		if (countByEmail == 1) {
			// if mail is present, return true
			return true;
		}
		return false;
	}

	@Override
	public UserDTO findByEmail(String email) {
		log.info("UserDTO findByEmail(String email)" + email);
		UserEntity entity = this.repo.findByEmail(email);
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	private Timer timer;
	private int duration =60; // duration of the OTP in seconds

	@Override
	public boolean sendResetMail(UserDTO dto) {

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.office365.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.starttls.enable", true);
		prop.put("mail.debug", true);
		prop.put("mail.smtp.auth", true);
		prop.put("mail.transport.protocol", "smtp");

		String fromEmail = "vinayhp.xworkz@outlook.com";
		String password = "Ns200@vini";
		String to = dto.getEmail();

		String randomPassword = generateRandomPassword();
		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}

		});
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(fromEmail));
			message.setSubject("Welcome to X-workz || Reset PASSWORD");
			message.setText("You choosen to reset the Password. \n\n\n UserId: " + dto.getUserId()
					+ "\n Random Password : " + randomPassword
					+ "\n\n\nKindly revert back for any queries, thank you for choosing Xworkz VDC.\n \n");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			Transport.send(message);
			log.info("RESET PASSWORD mail sent sucessfully");
			String storedPassword = dto.getPassword();
			dto.setPassword(randomPassword);
			dto.setUpdatedBy("SYSTEM");
			dto.setUpdatedOn(LocalDateTime.now());
			UserEntity entity = new UserEntity();
			BeanUtils.copyProperties(dto, entity);
			boolean randomPasswordUpdated = this.repo.updateRandomPassword(entity);
			if(randomPasswordUpdated) {
				dto.setPassword(storedPassword);
				start(dto);
				log.info("timer starts");
			}
			return randomPasswordUpdated;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

	private String generateRandomPassword() {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		int PASSWORD_LENGTH = 10;
		for (int i = 0; i < PASSWORD_LENGTH; i++) {
			int index = random.nextInt(chars.length());
			sb.append(chars.charAt(index));
		}
		return sb.toString();
	}

	/* Password Update */

	@Override
	public boolean authenticateUpdate(String userId, String password) {
		log.info("authenticateUpdate(String userId, String password) userId:" + userId + " Password:  " + password);
		UserEntity user = this.repo.findByUserId(userId);
		if (user != null && user.getPassword().equals(password)) {
			stop();
			log.info("timer stops");
			return true;
		}
		return false;
	}

	@Override
	public boolean compareNewPassword(String newPassword, String confirmNewPassword) {
		log.info("compareNewPassword");
		if (newPassword.equals(confirmNewPassword)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePassword(UserDTO dto, String newPassword) {
		log.info("updatePassword in service");
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(dto, entity);
		// password encoding
		String encodedPassword = passwordEncoder.encode(newPassword);
		passwordEncoder.upgradeEncoding(encodedPassword);
		entity.setPassword(encodedPassword);
		log.info(encodedPassword);
		boolean passwordUpdated = this.repo.updatePassword(entity);
		return passwordUpdated;
	}
	
	//To start the timer for password expiry
	 public void start(UserDTO dto) {
		    timer = new Timer();
		    timer.schedule(new TimerTask() {
		      @Override
		      public void run() {
		        // Disable the OTP field or delete the OTP from the database
		    	  UserEntity entity = new UserEntity();
		    	  BeanUtils.copyProperties(dto, entity);
		    	  repo.updatePassword(entity);
		        log.info("OTP expired");
		        
		      }
		    }, duration * 1000);
		  }
	
	 //To stop the timer for password correct credentials
		  public void stop() {
		    if (timer != null) {
		      timer.cancel();
		    }
		  }
}
