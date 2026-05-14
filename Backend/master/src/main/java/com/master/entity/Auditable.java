package com.master.entity;

import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Auditable {

	private Long createdBy;
	
	private LocalDateTime createdAt;
	
	private Long updatedBy;
	
	private LocalDateTime updatedAt;
	
	private Long deletedBy;
	
	private LocalDateTime deletedAt;
}
