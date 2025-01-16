package com.skyllx.system.repository;

import com.skyllx.system.entity.UserEntity;

public interface CMSignInRepo {

	UserEntity findByUserId(String userId);
	
	default UserEntity findById(int id) {
		return null;
	};
	
	default boolean updateAttempts(String userId, int attempts) {
		return false;
	};
	
	default boolean updateLock(String userId) {
		return false;
	};
	
	default UserEntity findByEmail(String email) {
		return null;
	}
	
	default boolean updateRandomPassword(UserEntity entity){
		return false;
	}
	
	default boolean updatePassword(UserEntity entity){
		return false;
	}

}
