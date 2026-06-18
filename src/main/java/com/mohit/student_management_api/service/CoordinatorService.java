package com.mohit.student_management_api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mohit.student_management_api.dto.CoordinatorRequestDto;
import com.mohit.student_management_api.dto.CoordinatorResponseDto;
import com.mohit.student_management_api.entity.Coordinator;
import com.mohit.student_management_api.entity.Department;
import com.mohit.student_management_api.exception.ResourceNotFoundException;
import com.mohit.student_management_api.repository.CoordinatorRepository;
import com.mohit.student_management_api.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoordinatorService {

	private final CoordinatorRepository coordinatorRepository;
	
	private final DepartmentRepository departmentRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	
	public Coordinator getCoordinatorObject(int id) {
		return coordinatorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Coordinator Not Found in Coordinator Service with id="+id));
	}
	
	public void createCoordinator(CoordinatorRequestDto coordinatorRequestDto) {
		Department department=departmentRepository.findById(coordinatorRequestDto.getDepartmentId())
				.orElseThrow(() -> new ResourceNotFoundException("department not found in Coordinator Service"));
		
		Coordinator coordinator=Coordinator.builder()
				.name(coordinatorRequestDto.getName())
				.email(coordinatorRequestDto.getEmail())
				.password(passwordEncoder.encode(coordinatorRequestDto.getPassword()))
				.contactNo(coordinatorRequestDto.getContactNo())
				.department(department)
				.build();
		
		log.info("password encoded as {}",passwordEncoder.encode(coordinatorRequestDto.getPassword()));
		log.info("Creating Coordinator for departmentId={}",coordinatorRequestDto.getDepartmentId());
		
		 coordinatorRepository.save(coordinator);
		 log.info("Coordinator Created for departmentId={}",coordinatorRequestDto.getDepartmentId());
			
	}

	public CoordinatorResponseDto getCoordinator(int id) {
		Coordinator coordinator=getCoordinatorObject(id);
		 log.info("Fetching Coordinator for coordinatorId={}",id);
			
		CoordinatorResponseDto coordinatorResponseDto= CoordinatorResponseDto.builder()
				.name(coordinator.getName())
				.email(coordinator.getEmail())
				.contactNo(coordinator.getContactNo())
				.departmentName(coordinator.getDepartment().getName())
				.build();
		 log.info("Fetched Coordinator for coordinatorId={}",id);
		 return coordinatorResponseDto;
	}

	public void deleteCoordinator(int id) {
		Coordinator coordinator=getCoordinatorObject(id);
		
		 log.info("Deleting Coordinator with coordinatorId={}",id);
		coordinatorRepository.delete(coordinator);
		 log.info("Coordinator Deleted successfully with coordinatorId={}",id);
	}

}
