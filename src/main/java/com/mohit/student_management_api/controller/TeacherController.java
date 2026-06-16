package com.mohit.student_management_api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.student_management_api.dto.TeacherRequestDto;
import com.mohit.student_management_api.service.TeacherService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
@RequestMapping("/teachers")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@PostMapping
	public ResponseEntity<?> createTeacher( 
		@Valid	@RequestBody TeacherRequestDto teacherRequestDto) {
		log.info("Request received to create Teacher for departmentId={}",teacherRequestDto.getDepartmentId());
		teacherService.addTeacher(teacherRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	@PutMapping("/{id}")
	public String updateTeacher(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to get department for departmentId={}",id);
		return "teacher updated";
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTeacher(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to delete teacher for teacherId={}",id);
		teacherService.deleteTeacher(id);
		return ResponseEntity.ok("Teacher deleted Successfully");
	}
	
	

}
