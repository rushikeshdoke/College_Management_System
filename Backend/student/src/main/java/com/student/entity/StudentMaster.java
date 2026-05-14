package com.student.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="student_master")
@Data
public class StudentMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId; 
	
	private String studentName;
	
	private Long categoryId;
}
