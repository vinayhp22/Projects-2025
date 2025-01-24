package com.skyllx.parkingrental.dto;

import java.io.Serializable;
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
public class UserParkingDTO implements Serializable, Comparable<UserParkingDTO>{
	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String location;
	private String vehicleNo;
	private String vehicleType;
	private String engineType;
	private String classification;
	private String term;
	private int price;
	private int discount;
	private int totalAmount;
	private String payment;
	private String fileName;
	private String originalFileName;
	private String contentType;
	private String createdDate;
	private String updatedDate;
	private boolean isActive;
	
	public UserParkingDTO() {
		log.info("Created: "+getClass().getSimpleName());
	}

	@Override
	public int compareTo(UserParkingDTO o) {
		location.compareTo(o.getLocation());
		return 0;
	}
}
