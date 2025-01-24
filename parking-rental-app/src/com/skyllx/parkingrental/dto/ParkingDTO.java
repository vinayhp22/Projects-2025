package com.skyllx.parkingrental.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ParkingDTO {

	private String email;
	private String password;
	private String loginTime;
	private String name;
	private String created_by;
	private String updated_by;
	private String created_date;
	private String updated_date;
	
	public ParkingDTO() {
		log.info("Created: "+getClass().getSimpleName());
	}
	
	
}
