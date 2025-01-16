package com.skyllx.system.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users_signup")
@NamedQuery(name = "countByUserId", query = "select Count(*) from UserEntity entity where entity.userId =:byUserId")
@NamedQuery(name = "countByEmail", query = "select Count(*) from UserEntity entity where entity.email =:byEmail")
@NamedQuery(name = "countByMobile", query = "select Count(*) from UserEntity entity where entity.mobile =:byMobile")
@NamedQuery(name = "findByUserId", query = "select entity from UserEntity entity where entity.userId =:byUserId")
@NamedQuery(name = "findById", query = "select entity from UserEntity entity where entity.id =:byId")
@NamedQuery(name = "findByEmail", query = "select entity from UserEntity entity where entity.email =:byEmail")
@NamedQuery(name = "updateAttempts", query = "update UserEntity entity set entity.attempts = :byAttempts where entity.userId =:byUserId")
@NamedQuery(name = "updateLock", query = "update UserEntity entity set entity.locked = true where entity.userId =:byUserId")
@NamedQuery(name = "updatePassword", query = "update UserEntity entity set entity.password = :byPassword where entity.userId =:byUserId")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "userId")
	private String userId;

	@Column(name = "email")
	private String email;

	@Column(name = "mobile")
	private Long mobile;

	@Column(name = "password")
	private String password;

	@Column(name = "agreement")
	private boolean agreement;

	@Column(name = "attempts")
	private int attempts;

	@Column(name = "locked")
	private boolean locked;
	
	@Column(name = "reset_pwd")
	private boolean reset_pwd;
	
	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "createdOn")
	private LocalDateTime createdOn;

	@Column(name = "updatedBy")
	private String updatedBy;

	@Column(name = "updatedOn")
	private LocalDateTime updatedOn;
	
	@Column(name = "profile_pic_name")
	private String profilePic;
}
