package com.skyllx.parkingrental.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Slf4j
public class ParkingInfoDTO implements Serializable, Comparable<ParkingInfoDTO> {
	private static final long serialVersionUID = 1L;
	
	private String location;
	private String vehicleType;
	private String engineType;
	private String classification;
	private String term;
	private int price;
	private String discount;
	
	public ParkingInfoDTO() {
		log.info("Created: "+getClass().getSimpleName());
	}

	@Override
	public int compareTo(ParkingInfoDTO o) {
		this.getLocation().equals(o.getLocation());
		return 0;
	}
}
