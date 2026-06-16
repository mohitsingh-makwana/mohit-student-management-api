package com.mohit.student_management_api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.student_management_api.dto.CoordinatorResponseDto;
import com.mohit.student_management_api.dto.CourseResponseDto;
import com.mohit.student_management_api.dto.MarksResponseDto;
import com.mohit.student_management_api.dto.StudentRequestDto;
import com.mohit.student_management_api.dto.StudentResponseDto;

import com.mohit.student_management_api.enums.FeeStatus;
import com.mohit.student_management_api.service.StudentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@Validated
@Slf4j
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	@PostMapping
	public ResponseEntity<?> createStudent(@Valid @RequestBody StudentRequestDto studentRequestDto) {
		log.info("Request received to create Student for departmentId={} and classroomId",
				studentRequestDto.getDepartmentId(),
				studentRequestDto.getClassroomId());
		studentService.createStudent(studentRequestDto);
	
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	
	@GetMapping("/{rollNo}")
	public ResponseEntity<StudentResponseDto> getStudent(@PathVariable @Positive(message = "Please enter a valid id") int rollNo) {
		log.info("Request received to get student for rollNo={}",rollNo);
		StudentResponseDto studentResponseDto=studentService.getStudentById(rollNo);
		
		if(studentResponseDto==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(studentResponseDto);
	}
	
	
	
	@DeleteMapping("/{rollNo}")
	public ResponseEntity<?> deleteStudent(@PathVariable @Positive(message = "Please enter a valid id") int rollNo) {
		log.info("Request received to delete student for rollNo={}",rollNo);
		studentService.deleteStudent(rollNo);
		return ResponseEntity.noContent().build();
	}
	
	
	
	@PutMapping("/{id}")
	public String updateCompleteStudent(@PathVariable int id) {
		log.info("Request received to update student for rollNo={}",id);
		return "student update ->Rishabh";
	}
	
	@PatchMapping("/{rollNo}/attendance")
	public ResponseEntity<String> updateStudent(@PathVariable int rollNo,@RequestParam double attendance) {
		log.info("Request received to update attendance for rollNo={}",rollNo);
		studentService.updateAttendance(rollNo,attendance);
		return ResponseEntity.ok("Attendance Updated "+attendance);
	}
	
	@PatchMapping("/{id}/fee-status")
	public ResponseEntity<?> updateStudentFee(@PathVariable int id,@RequestParam double fee,@RequestParam(required = false) FeeStatus feeStatus) {
		log.info("Request received to update student fee rollNo={}",id);
		studentService.updateStudentFee(id,fee,feeStatus);
		
		return ResponseEntity.ok("Fees Updated Successfully");
	}
	
	
	
	@GetMapping("/{rollNo}/coordinator")
	public ResponseEntity<CoordinatorResponseDto> getCoordinatorOfStudent(@PathVariable int rollNo) {
		log.info("Request received to get coordinator for rollNo={}",rollNo);
	CoordinatorResponseDto coordinatorResponseDto=studentService.getCoordinator(rollNo);
		return ResponseEntity.ok(coordinatorResponseDto);
	}
	
	
	
	@GetMapping("{rollNo}/courses")
	public ResponseEntity<List<CourseResponseDto>> getCourses(@PathVariable int rollNo) {
		log.info("Request received to get courses of rollNo={}",rollNo);
		List<CourseResponseDto> courseResponseDto= studentService.getCourses(rollNo);
		return ResponseEntity.ok(courseResponseDto);
	}
	
	@GetMapping("{rollNo}/marks")
	public ResponseEntity<List<MarksResponseDto>> getMarks(@PathVariable int rollNo) {
		log.info("Request received to get marks for rollNo={}",rollNo);
		List<MarksResponseDto> marksResponseDto= studentService.getMarks(rollNo);
		return ResponseEntity.ok(marksResponseDto);
	}
	
	
}
