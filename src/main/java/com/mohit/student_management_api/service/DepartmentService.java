package com.mohit.student_management_api.service;


import java.util.List;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mohit.student_management_api.dto.ClassRoomResponseDto;
import com.mohit.student_management_api.dto.CoordinatorResponseDto;
import com.mohit.student_management_api.dto.CourseResponseDto;
import com.mohit.student_management_api.dto.DepartmentRequestDto;
import com.mohit.student_management_api.dto.StudentResponseDto;
import com.mohit.student_management_api.dto.TeacherResponseDto;
import com.mohit.student_management_api.entity.ClassRoom;
import com.mohit.student_management_api.entity.Coordinator;
import com.mohit.student_management_api.entity.Course;
import com.mohit.student_management_api.entity.Department;
import com.mohit.student_management_api.entity.Student;
import com.mohit.student_management_api.entity.Teacher;
import com.mohit.student_management_api.exception.ResourceNotFoundException;
import com.mohit.student_management_api.mapper.ClassroomMapper;
import com.mohit.student_management_api.mapper.StudentMapper;
import com.mohit.student_management_api.repository.ClassRoomRepository;
import com.mohit.student_management_api.repository.DepartmentRepository;
import com.mohit.student_management_api.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentService {
	
	private final DepartmentRepository departmentRepository;
	private final StudentMapper studentMapper;
	private final ClassroomMapper classroomMapper;
	private final StudentRepository studentRepository;
	private final ClassRoomRepository classRoomRepository;
	
	public Department getDepartmentObject(int id) {
		Department department=departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department Not found with id="+id));
		return department;
	}

	public void createDepartment(DepartmentRequestDto departmentRequestDto) {
		Department department=Department.builder()
				.name(departmentRequestDto.getName())
				.id(departmentRequestDto.getId())
				.build();
		log.info("Creating Department");
		
		departmentRepository.save(department);
		log.info("department created");
		
	
	}

	public String getDepartment(int id) {
	Department department=getDepartmentObject(id);
	log.info("Fetching department");
		String name= department.getName();
	log.info("Department fetched");
	return name;
	}

	public Page<StudentResponseDto> getStudentsByDepartmentId(int id, int page, int size) {
			log.info("Fetching  Students for departmentId={}",id);
		Pageable pageable=PageRequest.of(page, size);
			
		Page<Student> students=studentRepository.getStudentsByDepartmentId(id,pageable);
		log.info("Fetched {} Students for departmentId={}",students.getSize(),id );
		
		return students.map(studentMapper::toStudentResponseDto);
	}

	public Page<ClassRoomResponseDto> getClassrooms(int id, int page, int size) {
	
		log.info("Fetching  Classrooms for departmentId={}",id);
		
		Pageable pageable=PageRequest.of(page, size);
		Page<ClassRoom> classRooms=classRoomRepository.findClassRoomsByDepartmentId(id,pageable);
		log.info("Fetched {} classrooms for departmentId={}",classRooms.getSize(),id);
		
		return classRooms.map(classroomMapper::toClassRoomResponseDto);
	}

	public List<TeacherResponseDto> getTeachers(int id) {
		Department department=getDepartmentObject(id);
		log.info("Fetching  Teachers for departmentId={}",id);
		
		List<Teacher> teachers=department.getTeachers();
		log.info("Fetched {} Teachers for departmentId={}",teachers.size(),id );
		
		if(teachers==null) {
			return List.of();
			}
		
		return teachers.stream()
				.map(teacher->TeacherResponseDto.builder()
						.name(teacher.getName())
						.email(teacher.getEmail())
						.contactNo(teacher.getContactNo())
						.departmentName(department.getName())
						.build()).toList();
	}

	public List<CoordinatorResponseDto> getCoordinators(int id) {
		Department department=getDepartmentObject(id);
		log.info("Fetching  Coordinators for departmentId={}",id);
		
		String departmentName=department.getName();
		List<Coordinator> coordinators=department.getCoordinators();
		log.info("Fetched {} Coordinators for departmentId={}",coordinators.size(),id );
		
		
		return coordinators.stream()
				.map(coordinator->CoordinatorResponseDto.builder()
						.name(coordinator.getName())
						.email(coordinator.getEmail())
						.contactNo(coordinator.getContactNo())
						.departmentName(departmentName)
						.build()).toList();
	}

	public List<CourseResponseDto> getCourses(int id) {
		Department department=getDepartmentObject(id);
		log.info("Fetching  Courses for departmentId={}",id);
		
		List<Course> courses=department.getCourses();
		log.info("Fetched {} Courses for departmentId={}",courses.size(),id );
		
		return courses.stream()
				.map(course->CourseResponseDto.builder()
						.id(course.getId())
						.name(course.getCourseName())
						.teacherName(course.getTeacher().getName())
						.departmentName(course.getDepartment().getName())
						.build())
						.toList();
	}

}
