package com.skyllx.system.repository;

import com.skyllx.system.entity.UserEntity;

public interface CMSignUpRepo {

	boolean save(UserEntity entity);
	
	default Long countByUserId(String userId) {
		return null;
	}
	
	default Long countByEmail(String email) {
		return null;
	}
	
	default Long countByMobile(Long mobile) {
		return null;
	}
}
