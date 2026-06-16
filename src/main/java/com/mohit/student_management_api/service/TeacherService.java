package com.mohit.student_management_api.service;


import org.springframework.stereotype.Service;

import com.mohit.student_management_api.dto.TeacherRequestDto;
import com.mohit.student_management_api.entity.Department;
import com.mohit.student_management_api.entity.Teacher;
import com.mohit.student_management_api.exception.ResourceNotFoundException;
import com.mohit.student_management_api.repository.DepartmentRepository;
import com.mohit.student_management_api.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherService {
	
	private final TeacherRepository teacherRepository;
	private final DepartmentRepository departmentRepository;

	public void addTeacher(TeacherRequestDto teacherRequestDto) {
		
		Department department=departmentRepository.findById(teacherRequestDto.getDepartmentId())
												   .orElseThrow(() -> new ResourceNotFoundException("Department Not Found in Teacher Service"));
		Teacher teacher =Teacher.builder()
				.id(teacherRequestDto.getId())
				.name(teacherRequestDto.getName())
				.email(teacherRequestDto.getEmail())
				.contactNo(teacherRequestDto.getContactNo())
				.department(department)
				.build();

		log.info("Creating teacher with departmentId={} ",teacherRequestDto.getDepartmentId());
		teacherRepository.save(teacher);

		log.info("Teacher created with departmentId={} ",teacherRequestDto.getDepartmentId());
	}

	public void deleteTeacher(int id) {
		Teacher teacher=teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher not found with teacherId="+id));

		log.info("Deleting Teacher with teacherId={} ",id);
		teacherRepository.delete(teacher);

		log.info("Teacher deleted successfully with teacherId={} ",id);
			
	}

}
