package com.student.response.dto;

import com.student.request.dto.CategoryDto;

import lombok.Data;

@Data
public class StudentResponseDto {

	private Long studentId;
	
	private String studentName;
	
	private CategoryDto categoryDto;
}
