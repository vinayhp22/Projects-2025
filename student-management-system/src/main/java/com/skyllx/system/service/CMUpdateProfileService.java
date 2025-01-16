package com.skyllx.system.service;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.skyllx.system.dto.UserDTO;

public interface CMUpdateProfileService {

	Set<ConstraintViolation<UserDTO>> validateAndUpdate(UserDTO dto);
}
