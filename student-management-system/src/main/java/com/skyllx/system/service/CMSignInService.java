package com.skyllx.system.service;

import com.skyllx.system.dto.UserDTO;

public interface CMSignInService {

	boolean authenticateUser(String userId, String password);
	
	default UserDTO findByUserId(String userId) {
		return null;
	};
	
	default UserDTO findById(int id) {
		return null;
	};
	
	default void updateAttempts(String userId, int attempts) {};

	default void updateLock(String userId) {};

	default boolean checkEmail(String email) {
		return false;
	};
	
	default UserDTO findByEmail(String email) {
		return null;
	};

	default boolean sendResetMail(UserDTO dto) {
		return false;
	};
	
	default boolean authenticateUpdate(String userId, String password)  {
		return false;
	};
	
	default boolean compareNewPassword(String newPassword, String confirmNewPassword) {
		return false;
	};
	
	default boolean updatePassword(UserDTO dto, String newPassword) {
		return false;
	};
		
}