package com.mohit.student_management_api.service;


import org.springframework.stereotype.Service;

import com.mohit.student_management_api.dto.CourseRequestDto;
import com.mohit.student_management_api.dto.TeacherResponseDto;
import com.mohit.student_management_api.entity.ClassRoom;
import com.mohit.student_management_api.entity.Course;
import com.mohit.student_management_api.entity.Department;
import com.mohit.student_management_api.entity.Teacher;
import com.mohit.student_management_api.exception.ResourceNotFoundException;
import com.mohit.student_management_api.repository.ClassRoomRepository;
import com.mohit.student_management_api.repository.CourseRepository;
import com.mohit.student_management_api.repository.DepartmentRepository;
import com.mohit.student_management_api.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseService {
	
	private final CourseRepository courseRepository;
	private final DepartmentRepository departmentRepository;
	private final TeacherRepository teacherRepository;
	private final ClassRoomRepository classRoomRepository;

	public void addCourse(CourseRequestDto courseRequestDto) {
		Department department=departmentRepository.findById(courseRequestDto.getDepartmentId())
												  .orElseThrow(() -> new RuntimeException("Department Not Found"));
		Teacher teacher=teacherRepository.findById(courseRequestDto.getTeacherId())
										 .orElseThrow(() -> new RuntimeException("Teacher Not found"));
		
	 	ClassRoom classroom=classRoomRepository.findById(courseRequestDto.getClassroomId()).orElseThrow(() -> new ResourceNotFoundException("ClassRoom Not Found with the id="+courseRequestDto.getClassroomId()));
		
		Course course=Course.builder()
				.id(courseRequestDto.getId())
				.courseName(courseRequestDto.getName())
				.department(department)
				.teacher(teacher)
				.classroom(classroom)
				.build();
				
		log.info("Creating Course for departmentId={} and classroomId={}",courseRequestDto.getDepartmentId(),courseRequestDto.getClassroomId());
		
		courseRepository.save(course);
		log.info("Course created for departmentId={} and classroomId={}",courseRequestDto.getDepartmentId(),courseRequestDto.getClassroomId());
		
	}

	public TeacherResponseDto getTeacher(String courseId) {
		
		log.info("Fetching teacher with courseId={}",courseId);
		Course course=courseRepository.findById(courseId)
				.orElseThrow(()->new ResourceNotFoundException("course not found with id="+courseId));
		Teacher teacher=course.getTeacher();	
		TeacherResponseDto teacherResponseDto=TeacherResponseDto.builder()
												.name(teacher.getName())
												.email(teacher.getEmail())
												.contactNo(teacher.getContactNo())
												.departmentName(teacher.getDepartment().getName())
												.build();
		log.info("Fetched Teacher with courseId={}",courseId);
		
		return teacherResponseDto;
	}

}
