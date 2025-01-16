package com.skyllx.system.service;

import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.skyllx.system.dto.UserDTO;
import com.skyllx.system.entity.UserEntity;
import com.skyllx.system.repository.CMSignUpRepo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CMSignUpServiceImpl implements CMSignUpService {

	@Autowired
	private CMSignUpRepo repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Set<ConstraintViolation<UserDTO>> validateAndSave(UserDTO dto) {
		log.info("validateAndSave in CMSignUpServiceImpl" + dto);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<UserDTO>> violations = validator.validate(dto);
		Long countByUserId = this.repo.countByUserId(dto.getUserId());
		Long countByEmail = this.repo.countByEmail(dto.getEmail());
		Long countByMobile = this.repo.countByMobile(dto.getMobile());
		if (countByUserId != 0 || countByEmail != 0 || countByMobile != 0) {
			if (violations != null && !violations.isEmpty()) {
				log.info("Violations in dto" + dto);
				return violations;
			}
		}
		log.info("There is no violations in dto, can save" + dto);

		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(dto, entity);
		// password encoding
		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		passwordEncoder.upgradeEncoding(encodedPassword);
		entity.setPassword(encodedPassword);
		log.info(entity.getPassword());

		boolean saved = this.repo.save(entity);
		log.info("Entity" + entity + "; saved : " + saved);
		return Collections.emptySet();
	}

	@Override
	public Long countByUserId(String userId) {
		log.info("countByUserId(String userId) " + userId);
		Long countByUserId = this.repo.countByUserId(userId);
		log.info("countByUserId : "+countByUserId);
		return countByUserId;
	}

	@Override
	public Long countByEmail(String email) {
		log.info("countByEmail(String email) " + email);
		Long countByEmail = this.repo.countByEmail(email);
		log.info("countByEmail : "+countByEmail);
		return countByEmail;
	}

	@Override
	public Long countByMobile(Long mobile) {
		log.info("countByMobile(Long mobile) " + mobile);
		Long countByMobile = this.repo.countByMobile(mobile);
		log.info("countByMobile : "+countByMobile);
		return countByMobile;
	}

	@Override
	public boolean sendEmail(UserDTO dto) {

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

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}

		});
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(fromEmail));
			message.setSubject("Welcome to X-workz");
			message.setText("Registration Completed. \n\n\n UserId: "+dto.getUserId()+"\n Password : "+dto.getPassword()+"\n\n\nKindly revert back for any queries, thank you for choosing Xworkz VDC.\n " );
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			Transport.send(message);
			log.info("mail sent sucessfully");
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}


}
