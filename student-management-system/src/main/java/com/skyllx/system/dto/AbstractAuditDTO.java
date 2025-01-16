package com.skyllx.system.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Table;
import lombok.Data;

@Table(name = "signup_table")
@Data
public class AbstractAuditDTO implements Serializable {
	
	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "createdOn")
	private LocalDateTime createdOn;

	@Column(name = "updatedBy")
	private String updatedBy;

	@Column(name = "updatedOn")
	private LocalDateTime updatedOn;

}
