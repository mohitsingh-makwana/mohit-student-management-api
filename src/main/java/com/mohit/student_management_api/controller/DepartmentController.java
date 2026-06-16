package com.mohit.student_management_api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.student_management_api.dto.ClassRoomResponseDto;
import com.mohit.student_management_api.dto.CoordinatorResponseDto;
import com.mohit.student_management_api.dto.CourseResponseDto;
import com.mohit.student_management_api.dto.DepartmentRequestDto;
import com.mohit.student_management_api.dto.StudentResponseDto;
import com.mohit.student_management_api.dto.TeacherResponseDto;
import com.mohit.student_management_api.service.DepartmentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping
	public ResponseEntity<String> createDepartment(@Valid @RequestBody DepartmentRequestDto departmentRequestDto){
		log.info("Request received to create Department");
		departmentService.createDepartment(departmentRequestDto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<String> getDepartmentById(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to get department for departmentId={}",id);
		String name=departmentService.getDepartment(id);
		return ResponseEntity.ok(name);
	}
	
	@GetMapping("/{id}/students")
	public ResponseEntity<List<StudentResponseDto>> getStudentsByDepartmentId(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to get Students for departmentId={}",id);
		List<StudentResponseDto> list=departmentService.getStudentsByDepartmentId(id);
		
		return ResponseEntity.ok(list);
	}
	@GetMapping("/{id}/classrooms")
	public ResponseEntity<List<ClassRoomResponseDto>> getClassRoomsByDepartmentId(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to get Classrooms for departmentId={}",id);
		List<ClassRoomResponseDto> list=departmentService.getClassrooms(id);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}/Teachers")
	public ResponseEntity<List<TeacherResponseDto>> getDepartmentTeachers(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to get teachers for departmentId={}",id);
		List<TeacherResponseDto> teachersResponseDtos=departmentService.getTeachers(id); 
		
		return ResponseEntity.ok(teachersResponseDtos);
	}
	
	@GetMapping("/{id}/coordinators")
	public ResponseEntity<List<CoordinatorResponseDto>> getCoordinatorsByDepartmentId(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to get coordinators for departmentId={}",id);
		List<CoordinatorResponseDto> coordinatorResponseDto=departmentService.getCoordinators(id);
		return ResponseEntity.ok(coordinatorResponseDto);
	}
	
	@GetMapping("/{id}/courses")
	public ResponseEntity<List<CourseResponseDto>> getCoursesByDepartmentById(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to get courses for departmentId={}",id);
		List<CourseResponseDto> coursesCourseResponseDtos=departmentService.getCourses(id);
		return ResponseEntity.ok(coursesCourseResponseDtos);
	}


	@PutMapping("/{id}")
	public String updateDepartmentById(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to update department for departmentId={}",id);
		return "Department Updated";
	}


}
