package com.mohit.student_management_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.student_management_api.dto.MarksRequestDto;
import com.mohit.student_management_api.dto.MarksResponseDto;
import com.mohit.student_management_api.service.MarksService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
@RequestMapping("/marks")
public class MarksController {
	
	@Autowired
	private MarksService marksService;
	
	@PostMapping
	public ResponseEntity<String> uploadMarks(@Valid @RequestBody MarksRequestDto marksRequestDto) {
		log.info("Request received to upload marks for studentId={}and courseId={}",
				marksRequestDto.getStudentId(),
				marksRequestDto.getCourseId());
		marksService.uploadMarks(marksRequestDto);
		
		return ResponseEntity.ok("marks Uploaded");
	}
	
	@GetMapping
	public ResponseEntity<List<MarksResponseDto>> getMarksByCourseIdAndStudentId(@RequestParam String courseId,
								@RequestParam @Positive(message = "Please enter a valid id") int studentId) {
		log.info("Request received to get marks for studentId={} and courseId={}",studentId,courseId);
	List<MarksResponseDto> marksResponseDto=marksService.getStudentMarks(courseId,studentId);
		
	if(marksResponseDto==null) {
		return ResponseEntity.notFound().build();
	}
		return ResponseEntity.ok(marksResponseDto);
	}

}
