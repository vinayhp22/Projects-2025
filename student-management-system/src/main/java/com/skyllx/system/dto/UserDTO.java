package com.skyllx.system.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.skyllx.system.violations.EmailCheck;
import com.skyllx.system.violations.MobileCheck;
import com.skyllx.system.violations.UserIdCheck;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class UserDTO extends AbstractAuditDTO{

	private int id;

	@UserIdCheck
	@NotNull(message = "userId can't be null")
	@Size(min = 6, max = 10, message = "userId can't be less than 6 or more than 10 characters")
	private String userId;
	
	@EmailCheck
	@NotNull(message = "email can't be null")
	private String email;
	
	@MobileCheck
	@NotNull(message = "mobile number can't be null")
	private Long mobile;
	
	@NotNull(message = "password can't be null")
	@Size(min = 6, max = 10, message = "password can't be less than 6 or more than 10 characters")
	private String password;
	
	@NotNull(message = "agreement can't be null")
	private boolean agreement;
	
	private int attempts;
	
	private boolean locked;
	
	private boolean reset_pwd;

	private String profilePic;

}
