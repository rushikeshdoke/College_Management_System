package com.student.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.student.entity.StudentMaster;
import com.student.mapper.StudentMapper;
import com.student.repository.StudentMasterRepository;
import com.student.request.dto.AddStudentDto;
import com.student.response.dto.StudentResponseDto;
import com.student.service.StudentMasterService;

@Service
public class StudentMasterServiceImpl implements StudentMasterService{
	
	@Autowired
	private StudentMasterRepository studentMasterRepository;
	
	@Autowired
	private StudentMapper studentMapper;
	
	Map<String, Object> response = new HashMap<>();

	@Override
	public ResponseEntity<Map<String, Object>> addStudent(AddStudentDto addStudentDto) {
		
		try {
		StudentMaster studentMaster=studentMapper.toStudentMaster(addStudentDto);
		StudentMaster savedStudent = studentMasterRepository.save(studentMaster);
		
		response.put("succes", true);
		response.put("message", "Student successfully added...!");
		response.put("data", savedStudent);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		}catch(Exception e) {
			throw new RuntimeException("Failed to add student"+e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> getStudentList() {
		
		List<StudentMaster> studentMasterList=studentMasterRepository.findAll();
		
		List<StudentResponseDto> studentResponseList=studentMasterList.stream().map(studentMapper::toStudentResponseDto).toList();
		
		response.put("success", true);
		response.put("message", "Student list fetched successfully...!");
		response.put("data", studentResponseList);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Object>> getStudentById(Long studentId) {
		
		StudentMaster studentMaster = studentMasterRepository.findById(studentId).orElseThrow(()->new RuntimeException("Student not found...!"));
		StudentResponseDto studentResponseDto = studentMapper.toStudentResponseDto(studentMaster);
		
		response.put("success", true);
		response.put("message", "Student fetched successfully...!");
		response.put("data", studentResponseDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
