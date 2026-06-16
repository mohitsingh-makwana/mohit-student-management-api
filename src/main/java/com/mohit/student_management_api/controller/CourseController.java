 package com.mohit.student_management_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.student_management_api.dto.CourseRequestDto;
import com.mohit.student_management_api.dto.TeacherResponseDto;
import com.mohit.student_management_api.service.CourseService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@PostMapping
	public ResponseEntity<String> createCourse (@Valid @RequestBody CourseRequestDto courseRequestDto) {
		log.info("Request received to create Course for departmentId={} and classroomId={}",
				courseRequestDto.getDepartmentId(),
				courseRequestDto.getClassroomId());
		courseService.addCourse(courseRequestDto);
		return ResponseEntity.ok("course created");
	}
	
	@GetMapping("/{courseId}/teacher")
	public ResponseEntity<TeacherResponseDto> getTeacherByCourseId(String courseId) {
		log.info("Request received to get Teacher for courseId={}",courseId);
		TeacherResponseDto teacherResponseDto=courseService.getTeacher(courseId);
		
		return ResponseEntity.ok(teacherResponseDto);
	}
	
	

}
