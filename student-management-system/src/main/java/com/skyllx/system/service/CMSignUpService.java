package com.skyllx.system.service;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.skyllx.system.dto.UserDTO;

public interface CMSignUpService {

	Set<ConstraintViolation<UserDTO>> validateAndSave(UserDTO dto);
	
	default Long countByUserId(String userId) {
		return null;
	}
	
	default Long countByEmail(String email) {
		return null;
	}
	
	default Long countByMobile(Long mobile) {
		return null;
	}
	
	default boolean sendEmail(UserDTO dto) {
		return false;
	}

	

	
}