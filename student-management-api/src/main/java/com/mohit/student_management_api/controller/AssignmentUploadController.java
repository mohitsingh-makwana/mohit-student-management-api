package com.mohit.student_management_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.student_management_api.dto.AssignmentUploadRequestDto;
import com.mohit.student_management_api.dto.AssignmentUploadResponseDto;
import com.mohit.student_management_api.service.AssignmentUploadService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
@RequestMapping("/assignmentUpload")
public class AssignmentUploadController {
	@Autowired
	private AssignmentUploadService assignmentUploadService;
	
	@PostMapping
	public ResponseEntity<String> uploadAssignment(@Valid @ModelAttribute AssignmentUploadRequestDto assignmentUploadRequestDto) {
		
		log.info("Received request to upload assignment for studentId={} and AssignmentIssuedId={}",
				assignmentUploadRequestDto.getStudentId(),
				assignmentUploadRequestDto.getAssignmentIssuedId());
		assignmentUploadService.uploadAssignment(assignmentUploadRequestDto);
		return ResponseEntity.ok("assignment uploaded");
	}
	
	@GetMapping(params= {"assignmentIssuedId","studentRoll"})
	public ResponseEntity<List<AssignmentUploadResponseDto>> getAssignmentUploadedByAssignmentIssuedIdAndStudentId(@RequestParam
			@Positive(message = "Please enter a valid id") int assignmentIssuedId
			,@RequestParam @Positive(message = "Please enter a valid id") int studentId) {
		
		log.info("Recieved request to get uploaded assignments for studentId={} and AssignmentIssuedId={}",studentId,assignmentIssuedId);
		List<AssignmentUploadResponseDto> assignmentUploadResponseDtos=assignmentUploadService.getAssignmentUploadedByAssignmentIssuedIdAndStudentId(assignmentIssuedId, studentId);
		return ResponseEntity.ok(assignmentUploadResponseDtos);
	}
	
	/*@GetMapping(params= {"courseId","studentId"})
	public ResponseEntity<List<AssignmentUploadResponseDto>> getAssignmentUploadedByCourseIdAndStudentId(@RequestParam String courseId,@RequestParam int studentId) {
		List<AssignmentUploadResponseDto> assignmentUploadResponseDtos=assignmentUploadService.getAssignmentUploadedByCourseIdAndStudentId(courseId,studentId);
		return ResponseEntity.ok(assignmentUploadResponseDtos);
	}
*/
}
