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
@Slf4j
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "admin_login")
@NamedQuery(name="findAll", query = "select aa from ParkingEntity as aa")
@NamedQuery(name="findNameAndLoginTime", query = "select aa.name, aa.loginTime from ParkingEntity as aa where aa.id=:ss")
@NamedQuery(name="findByEmail", query = "select aa from ParkingEntity as aa where aa.adminEmail=:ss")
public class ParkingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="EMAIL_ID")
	private String adminEmail;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="CREATED_BY")
	private String created_by;
	
	@Column(name="UPDATED_BY")
	private String updated_by;
	
	@Column(name="CREATED_DATE")
	private String created_date;
	
	@Column(name="UPDATED_DATE")
	private String updated_date;
	
	@Column(name="LOGIN_TIME")
	private String loginTime;
		
	public ParkingEntity() {
		log.info("Created: "+getClass().getSimpleName());
	}
}
