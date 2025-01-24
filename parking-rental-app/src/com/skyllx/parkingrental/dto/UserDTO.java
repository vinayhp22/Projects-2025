package com.skyllx.parkingrental.dto;

import java.io.Serializable;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Slf4j
@EqualsAndHashCode
public class UserDTO implements Serializable, Comparable<UserDTO> {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String email;
	private long phoneNo;
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;
	private Integer otp;
	private int otpCount;
	private String AcctStatus;
	private LocalTime otpExpiryTime;
	
	
	public UserDTO() {
		log.info("created: "+this.getClass().getSimpleName());
	}
	@Override
	public int compareTo(UserDTO o) {
		name.compareTo(o.getName());
		return 0;
	}
}
