package com.skyllx.system.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TechnologyListDTO {

	private Long stockId;
	
	private int id;

	@NotNull(message = "Name can't be null")
	private String name;
	
	@NotNull(message = "Language can't be null")
	private String language;
	
	@NotNull(message = "Version can't be null")
	private String version;
	
	@NotNull(message = "Owner can't be null")
	private String owner;
	
	@NotNull(message = "Support From can't be null")
	private String supportFrom;
	
	@NotNull(message = "Support To can't be null")
	private String supportTo;
	
	@NotNull(message = "License can't be null")
	private String license;
	
	
	@NotNull(message = "OS Type can't be null")
	private String OSType;
	
	@NotNull(message = "openSource can't be null")
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