package com.mohit.student_management_api.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mohit.student_management_api.dto.NoticeRequestDto;
import com.mohit.student_management_api.service.NoticeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
@RequestMapping("/notices")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@PostMapping
	public ResponseEntity<String> createNotice(@Valid @ModelAttribute NoticeRequestDto noticeRequestDto) {
		log.info("Request received to create Notice for classroomId={}",noticeRequestDto.getClassroomId());
			noticeService.createNotice(noticeRequestDto);
		return ResponseEntity.ok("notice created");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteNotice(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		noticeService.deleteNotice(id);
		log.info("Request received to delete notice for noticeId={}",id);
		return ResponseEntity.ok("Notice deleted with id="+id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateNotice(@PathVariable @Positive(message = "Please enter a valid id") int id,@RequestParam(required = false) LocalDate lastDate 
			,@RequestParam(required = false) MultipartFile noticeDoc) {
		log.info("Request received to update notice for noticeId={}",id);
		noticeService.updateNotice(id,lastDate,noticeDoc);
		return ResponseEntity.ok("Notice updated");
	}
	
	
}
