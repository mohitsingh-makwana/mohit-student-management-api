package com.mohit.student_management_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.student_management_api.dto.AssignmentRequestDto;
import com.mohit.student_management_api.dto.AssignmentResponseDto;
import com.mohit.student_management_api.dto.AssignmentUploadResponseDto;
import com.mohit.student_management_api.service.AssignmentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
@RequestMapping("/assignmentIssued")
public class AssignmentIssuedController {

	@Autowired
	private AssignmentService assignmentService;
	
	
	@PostMapping
	public ResponseEntity<String> issueAssignment(@Valid @ModelAttribute AssignmentRequestDto assignmentRequestDto) {
		
		log.info("Received request to issue Assignment");
		assignmentService.issueAssignment(assignmentRequestDto);
		return ResponseEntity.ok("Assignment Uploaded");
	}
	
	@GetMapping
	public ResponseEntity<List<AssignmentResponseDto>> getIssuedAssignmentByClassRoomIdAndTeacherId(@RequestParam 
			@Positive(message = "Please enter a valid id") int classroomId
			,@RequestParam @Positive(message = "Please enter a valid id")int teacherId) {
		log.info(
				"Received request to get all issued assignment for classroomId={} and teacherId={}",
				classroomId,
				teacherId);
		List<AssignmentResponseDto> assignmentResponseDtos=assignmentService.getAssignments(classroomId,teacherId);
		return ResponseEntity.ok(assignmentResponseDtos);
	}
	
	@GetMapping("/{id}/assignmentUploaded")
	public ResponseEntity<Page<AssignmentUploadResponseDto>> getUploadedAssignmentByAssignmentIssuedId(@PathVariable @Positive(message = "Please enter a valid id")int id,
																									   @RequestParam(defaultValue = "0")int page,
																									   @RequestParam(defaultValue = "10")int size){
		log.info("Recieved request to get all uploaded assignment for assignmentIssuedId={}",id);
	Page<AssignmentUploadResponseDto> assignmentUploadResponseDtos=	assignmentService.getUploadedAssignment(id,page,size);
		return ResponseEntity.ok(assignmentUploadResponseDtos);
	}
}
