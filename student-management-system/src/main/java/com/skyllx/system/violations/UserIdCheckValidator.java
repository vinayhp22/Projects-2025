package com.skyllx.system.violations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skyllx.system.repository.CMSignUpRepo;
import com.skyllx.system.service.CMSignUpService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserIdCheckValidator implements ConstraintValidator<UserIdCheck, String> {

	@Autowired
	private CMSignUpRepo repo;
	
	@Autowired
	private CMSignUpService service;

	public UserIdCheckValidator() {
		log.info("Created :" + this.getClass());
		log.info("this.repo : "+this.repo);
		log.info("this.service : "+this.service);
	}
	@Override
	public void initialize(UserIdCheck constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		log.info("calling isValid, userId : " + value);
		if (this.repo != null) {
			Long countByUserId = repo.countByUserId(value);
			log.info("countByUserId : " + countByUserId);
			if (countByUserId == 0) {
				return true;
			}
		}
		return false;
	}

}
