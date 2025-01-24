package com.skyllx.parkingrental.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Slf4j
@Table(name="parking_details")
@NamedQuery(name="findByLTTCT", query="select aa from ParkingInfoEntity aa where aa.location=:lc and aa.vehicleType=:vtype and aa.engineType=:etype and aa.classification=:cls and aa.term=:trm")
@NamedQuery(name="findByPrice", query="select aa from ParkingInfoEntity aa where aa.price=:prc")
@NamedQuery(name="findByPriceAndDiscount", query="select aa from ParkingInfoEntity aa where aa.price=:prc and aa.discount=:dis")
@NamedQuery(name="findByLocation", query = "select entity from ParkingInfoEntity entity where entity.location=:lc")
public class ParkingInfoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="VEHICLE_TYPE")
	private String vehicleType;
	
	@Column(name="ENGINE_TYPE")
	private String engineType;
	
	@Column(name="CLASSIFICATION")
	private String classification;
	
	@Column(name="TERM")
	private String term;
	
	@Column(name="PRICE")
	private int price;
	
	@Column(name="DISCOUNT")
	private String discount;
	
	public ParkingInfoEntity(){
		log.info("Created: "+getClass().getSimpleName());
	}
}
