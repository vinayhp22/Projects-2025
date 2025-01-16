package com.skyllx.system.entity;

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
@Table(name = "technology" )
@NamedQuery(name = "list", query = "SELECT entity FROM TechnolgyListEntity entity where entity.id=:byId")
public class TechnolgyListEntity {

	@Id
	@Column(name = "stockId", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stockId;
	
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "language")
	private String language;

	@Column(name = "version")
	private String version;

	@Column(name = "owner")
	private String owner;

	@Column(name = "support_from")
	private String supportFrom;

	@Column(name = "support_to")
	private String supportTo;

	@Column(name = "license")
	private String license;

	@Column(name = "OS_type")
	private String OSType;

	@Column(name = "open_source")
	private Boolean openSource;
}
/*
 * CREATE TABLE `vinayhp-xworkz-cm`.`technology` ( `id` INT NOT NULL, `name`
 * VARCHAR(45) NULL, `language` VARCHAR(45) NULL, `version` DECIMAL(6,2) NULL,
 * `owner` VARCHAR(45) NULL, `support_from` VARCHAR(45) NULL, `support_to`
 * VARCHAR(45) NULL, `license` VARCHAR(45) NULL, `open_source` TINYINT(1) NULL,
 * `OS_type` VARCHAR(45) NULL, INDEX `id_idx` (`id` ASC) VISIBLE, CONSTRAINT
 * `id` FOREIGN KEY (`id`) REFERENCES `vinayhp-xworkz-cm`.`users_signup` (`id`)
 * ON DELETE NO ACTION ON UPDATE NO ACTION);
 */