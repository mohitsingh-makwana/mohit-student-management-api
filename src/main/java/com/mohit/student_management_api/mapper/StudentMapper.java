package com.mohit.student_management_api.mapper;

import org.springframework.stereotype.Component;

import com.mohit.student_management_api.dto.StudentResponseDto;
import com.mohit.student_management_api.entity.Student;

@Component
public class StudentMapper {

	public StudentResponseDto toStudentResponseDto(Student student) {
		return StudentResponseDto.builder()
				.rollNo(student.getRollNo())
				.name(student.getName())
				.email(student.getEmail())
				.contactNo(student.getContactNo())
				.address(student.getAddress())
				.attendance(student.getAttendance())
				.build();
	}
}
