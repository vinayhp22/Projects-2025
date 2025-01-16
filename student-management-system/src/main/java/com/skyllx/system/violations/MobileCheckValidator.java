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
public class MobileCheckValidator implements ConstraintValidator<MobileCheck, Long> {

	private CMSignUpService service;

	public MobileCheckValidator() {
		log.info("Created :" + this.getClass());
	}
	@Autowired
	private CMSignUpRepo repo;

	@Override
	public void initialize(MobileCheck constraintAnnotation) {
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		log.info("calling isValid, mobile : " + value);

		if (this.repo != null) {
			Long countByMobile = this.repo.countByMobile(value);
			log.info("countByMobile : " + countByMobile);
			if (countByMobile == 0) {
				return true;
			}
		}
		return false;
	}

}
