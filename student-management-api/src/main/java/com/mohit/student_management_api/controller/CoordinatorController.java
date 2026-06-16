package com.mohit.student_management_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.student_management_api.dto.CoordinatorRequestDto;
import com.mohit.student_management_api.dto.CoordinatorResponseDto;
import com.mohit.student_management_api.service.CoordinatorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
@RequestMapping("/coordinators")
public class CoordinatorController {
	
	@Autowired
	private CoordinatorService coordinatorService;
	
	@PostMapping
	public ResponseEntity<?> addCoordinator(@Valid @RequestBody CoordinatorRequestDto coordinatorRequestDto) {
		log.info("Request received to create coordinator for departmentId={}",coordinatorRequestDto.getDepartmentId());
		coordinatorService.createCoordinator(coordinatorRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CoordinatorResponseDto> getCoordinator(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to get coordinator for coordinatorId{} ",id);
		return ResponseEntity.ok(coordinatorService.getCoordinator(id));
	}
	
	@PutMapping("/{id}")
	public String updateCoordinator(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to update coordinator for coordinatorId={}",id);
		return "coordinator updated->Seweta Gupta";
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCoordinator(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to delete coordinator for coordinatorId={}",id);
		coordinatorService.deleteCoordinator(id);
		return ResponseEntity.ok("Coordinator Deleted");
	}
	
	
	
	

}
