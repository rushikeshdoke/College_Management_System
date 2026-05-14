package com.student.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.student.request.dto.AddStudentDto;

public interface StudentMasterService {

	ResponseEntity<Map<String, Object>> addStudent(AddStudentDto addStudentDto);

	ResponseEntity<Map<String, Object>> getStudentList();

	ResponseEntity<Map<String, Object>> getStudentById(Long studentId);

}
