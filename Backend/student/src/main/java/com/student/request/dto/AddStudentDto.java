package com.student.request.dto;

import lombok.Data;

@Data
public class AddStudentDto {

	private Long studentId;
	
	private String studentName;
	
	private Long categoryId;
}
