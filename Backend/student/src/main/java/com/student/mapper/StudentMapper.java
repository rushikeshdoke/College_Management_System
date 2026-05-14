package com.student.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.student.entity.StudentMaster;
import com.student.request.dto.AddStudentDto;
import com.student.request.dto.CategoryDto;
import com.student.response.dto.ApiResponse;
import com.student.response.dto.StudentResponseDto;

@Component
public class StudentMapper {
	
	@Autowired RestTemplate restTemplate;

	public StudentMaster toStudentMaster(AddStudentDto addStudentDto) {
		StudentMaster studentMaster=new StudentMaster();
		studentMaster.setStudentName(addStudentDto.getStudentName());
		studentMaster.setCategoryId(addStudentDto.getCategoryId());
		
		return studentMaster;
	}
	
	public StudentResponseDto toStudentResponseDto(
	        StudentMaster studentMaster) {

	    ResponseEntity<ApiResponse<CategoryDto>> response =
	            restTemplate.exchange(
	                    "http://localhost:8080/category/has-id/"
	                    + studentMaster.getCategoryId(),

	                    HttpMethod.GET,

	                    null,

	                    new ParameterizedTypeReference<
	                            ApiResponse<CategoryDto>>() {}
	            );

	    CategoryDto categoryDto =
	            response.getBody().getData();

	    StudentResponseDto studentResponseDto =
	            new StudentResponseDto();

	    studentResponseDto.setStudentId(
	            studentMaster.getStudentId()
	    );

	    studentResponseDto.setStudentName(
	            studentMaster.getStudentName()
	    );

	    studentResponseDto.setCategoryDto(
	            categoryDto
	    );

	    return studentResponseDto;
	}
}
