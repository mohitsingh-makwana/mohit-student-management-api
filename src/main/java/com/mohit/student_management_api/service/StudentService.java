package com.mohit.student_management_api.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mohit.student_management_api.dto.CoordinatorResponseDto;
import com.mohit.student_management_api.dto.CourseResponseDto;
import com.mohit.student_management_api.dto.MarksResponseDto;
import com.mohit.student_management_api.dto.StudentRequestDto;
import com.mohit.student_management_api.dto.StudentResponseDto;
import com.mohit.student_management_api.entity.ClassRoom;
import com.mohit.student_management_api.entity.Coordinator;
import com.mohit.student_management_api.entity.Course;
import com.mohit.student_management_api.entity.Department;
import com.mohit.student_management_api.entity.Student;
import com.mohit.student_management_api.enums.FeeStatus;
import com.mohit.student_management_api.exception.ResourceNotFoundException;
import com.mohit.student_management_api.repository.ClassRoomRepository;
import com.mohit.student_management_api.repository.CoordinatorRepository;
import com.mohit.student_management_api.repository.DepartmentRepository;
import com.mohit.student_management_api.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {


    private final DepartmentRepository departmentRepository;
	
	private final StudentRepository studentRepository;
	
	private final CoordinatorRepository coordinatorRepository;
	
	private final ClassRoomRepository classRoomRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	
	public Student getStudentObject(int id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student Not Found in Student Service with id="+id));
	}

	//Student Saving
	public void createStudent(StudentRequestDto studentRequestDto) {
		
	Department department=departmentRepository.findById(studentRequestDto.getDepartmentId())
											  .orElseThrow(()->new ResourceNotFoundException("Department Not Found!!"));
	Coordinator coordinator=coordinatorRepository.findById(studentRequestDto.getCoordinatorId())
			.orElseThrow(() -> new ResourceNotFoundException("Coordinator Not Found"));
	
	ClassRoom classRoom=classRoomRepository.findById(studentRequestDto.getClassroomId())
			.orElseThrow(() -> new RuntimeException("ClassRoom Not Found"));
		
		Student student=Student.builder()
				.rollNo(studentRequestDto.getRollNo())
				.name(studentRequestDto.getName())
				.email(studentRequestDto.getEmail())
				.password(passwordEncoder.encode(studentRequestDto.getRollNo()+"@123"))
				.contactNo(studentRequestDto.getContactNo())
				.address(studentRequestDto.getAddress())
				.attendance(studentRequestDto.getAttendance())
				.feeDue(studentRequestDto.getDueFee())
				.feeStatus(studentRequestDto.getFeeStatus())
				.department(department)
				.coordinator(coordinator)
				.classroom(classRoom)
				.build();
		log.info("Creating student with classroomId={} and departmentId={} ",
				studentRequestDto.getClassroomId(),
				studentRequestDto.getDepartmentId());
	
	studentRepository.save(student);
	log.info("Student created successfully with classroomId={} and departmentId={} ",
			studentRequestDto.getClassroomId(),
			studentRequestDto.getDepartmentId());

		}

	public StudentResponseDto getStudentById(int id) {
		Student student=getStudentObject(id);
		log.info("Fetching student with rollNo={} ",
				id);

		StudentResponseDto studentResponseDto=StudentResponseDto.builder()
				.rollNo(student.getRollNo())
				.name(student.getName())
				.email(student.getEmail())
				.contactNo(student.getContactNo())
				.address(student.getAddress())
				.attendance(student.getAttendance())
				.build();
		log.info("Fetched student with rollNo={} ",id);

		return studentResponseDto;
	}

	public void deleteStudent(int rollNo) {
		Student student=getStudentObject(rollNo);

		log.info("Deleting student with rollNO={} ",rollNo);
		studentRepository.delete(student);

		log.info("Deleted student with rollNo={} ",rollNo);
	}

	public void updateAttendance(int rollNo,double attendance) {
		Student student=getStudentObject(rollNo);

		log.info("Updating student attendance with rollNo={} ",rollNo);
		student.setAttendance(attendance);
		studentRepository.save(student);

		log.info("Attendance updated successfully of rollNo={} ",rollNo);
		
	}

	public CoordinatorResponseDto getCoordinator(int rollNo) {
		Student student=getStudentObject(rollNo);
		Coordinator coordinator=student.getCoordinator();
		
		CoordinatorResponseDto coordinatorResponseDto=CoordinatorResponseDto.builder()
				.name(coordinator.getName())
				.email(coordinator.getEmail())
				.contactNo(coordinator.getContactNo())
				.departmentName(coordinator.getDepartment().getName())
				.build();
		
		return coordinatorResponseDto;
	}

	

	public void updateStudentFee(int id, double fee, FeeStatus feeStatus) {
		Student student=getStudentObject(id);
		student.setFeeDue(fee);
		if(feeStatus!=null) {
		student.setFeeStatus(feeStatus);	
		}

		log.info("Updating fee of rollNo={} ",id);
		studentRepository.save(student);

		log.info("Fee updated successfully of rolllNo={} ",id);
	}
	
	public List<CourseResponseDto> getCourses(int rollNo) {
		Student student=getStudentObject(rollNo);

		log.info("Fetching courses of student with rollNo={} ",rollNo);
		ClassRoom classRoom=student.getClassroom();	
		
	List<CourseResponseDto> courseResponseDtos= classRoom.getCourses()
	        .stream()
	        .map(course -> CourseResponseDto.builder()
	                .id(course.getId())
	                .name(course.getCourseName())
	                .teacherName(course.getTeacher().getName())
	                .classroomName(course.getClassroom().getName())
	                .departmentName(course.getDepartment().getName())
	                .build())
	        .toList();

	log.info("Fetched {} courses of student with rollNo={} ",courseResponseDtos.size() ,rollNo);
	return courseResponseDtos;
	}

	public List<MarksResponseDto> getMarks(int rollNo) {
		Student student=getStudentObject(rollNo);
		
		return student.getMarks().stream()
				.map(marks->MarksResponseDto.builder()
						.rollNo(marks.getStudent().getRollNo())
						.studentName(marks.getStudent().getName())
						.examName(marks.getExamName())
						.marks(marks.getMarks())
						.courseName(marks.getCourse().getCourseName())
						.className(marks.getCourse().getClassroom().getName())
						.build()).toList();
	}
	
	

}
