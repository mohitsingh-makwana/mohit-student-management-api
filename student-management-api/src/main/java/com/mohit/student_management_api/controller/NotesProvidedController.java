package com.mohit.student_management_api.controller;

import java.io.IOException;
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

import com.mohit.student_management_api.dto.NotesProvidedRequestDto;
import com.mohit.student_management_api.dto.NotesProvidedResponseDto;
import com.mohit.student_management_api.service.NotesProvidedService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
@RequestMapping("/notesprovided")
public class NotesProvidedController {

	@Autowired
	private NotesProvidedService notesProvidedService;
	
	@PostMapping
	public ResponseEntity<String> uploadNotes(@Valid @ModelAttribute NotesProvidedRequestDto notesProvidedRequestDto) {
		log.info("Request received to upload notes for classroomId={} and courseId={}",notesProvidedRequestDto.getClassroomId(),
				notesProvidedRequestDto.getCourseId());
			try {
				notesProvidedService.uploadNotes(notesProvidedRequestDto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return ResponseEntity.ok("notes uploaded");
	}
	
	@GetMapping
	public ResponseEntity<List<NotesProvidedResponseDto>> getNotesByClassroomAndCourse(@RequestParam 
											@Positive(message = "Please enter a valid id") int classroomId,
											@RequestParam String courseId) {
		log.info("Request received to get notes for classroomId={} and courseId={}",classroomId,courseId);
		List<NotesProvidedResponseDto> notesProvidedResponseDtos=notesProvidedService.getNotes(classroomId,courseId);
		return ResponseEntity.ok(notesProvidedResponseDtos);
	}
}
