package com.skyllx.parkingrental.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
@EqualsAndHashCode
@ToString
@Entity
@Table(name="user_parking_details")
@NamedQuery(name="findAllByUserId", query = "select aa from UserParkingEntity aa where aa.userId=:uId and aa.isActive=:status")
@NamedQuery(name="findByVehicleNo", query = "select aa from UserParkingEntity aa where aa.vehicleNo=:vNo")
@NamedQuery(name="findAllEntities", query = "select aa from UserParkingEntity aa")
public class UserParkingEntity {

	@Id
	@Column(name="ID")
	private int id;
	@Column(name="USERID_FK")
	private int userId;
	@Column(name="VEHICLE_NO")
	private String vehicleNo;
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
	private int discount;
	@Column(name="TOTAL_AMT")
	private int totalAmount;
	@Column(name = "FILE_NAME")
	private String fileName;
	@Column(name = "PAYMENT")
	private String payment;
	@Column(name = "ORIGINAL_FN")
	private String originalFileName;
	@Column(name = "CONTENT_TYPE")
	private String contentType;
	@Column(name = "CREATED_DATE")
	private String createdDate;
	@Column(name = "UPDATED_DATE")
	private String updatedDate;
	@Column(name = "IS_ACTIVE")
	private boolean isActive;
	
	public UserParkingEntity() {
		log.info("Created: "+getClass().getSimpleName());
	}
}
