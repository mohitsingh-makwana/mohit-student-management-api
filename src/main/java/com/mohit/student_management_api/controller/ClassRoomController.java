package com.mohit.student_management_api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mohit.student_management_api.dto.AssignmentResponseDto;
import com.mohit.student_management_api.dto.ClassRoomRequestDto;
import com.mohit.student_management_api.dto.CoordinatorResponseDto;
import com.mohit.student_management_api.dto.CourseResponseDto;
import com.mohit.student_management_api.dto.NotesProvidedResponseDto;
import com.mohit.student_management_api.dto.NoticeResponseDto;
import com.mohit.student_management_api.dto.StudentResponseDto;
import com.mohit.student_management_api.dto.TeacherResponseDto;
import com.mohit.student_management_api.entity.AssignmentIssued;
import com.mohit.student_management_api.entity.NotesProvided;
import com.mohit.student_management_api.service.ClassRoomService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@Validated
@Slf4j
@RequestMapping("/classrooms")
public class ClassRoomController {
	
	@Autowired
	private ClassRoomService classRoomService;
	
	@PostMapping
	public ResponseEntity<?> createClassRoom(@Valid @RequestBody ClassRoomRequestDto classRoomRequestDto) {
		
		log.info("Received request to create Classroom for departmentId={} and coordinatorId={} ",
				classRoomRequestDto.getDepartmentId(),
				classRoomRequestDto.getCoordinatorId());
		classRoomService.createClassRoom(classRoomRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	public String updateClassRoomById(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to update classroom for classroomId={}",id);
		return "classroom updated";
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteClassRoomById(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to delete classroom for classroomId={}",id);
		classRoomService.deleteClassRoom(id);
		return ResponseEntity.ok( "classroom deleted");
	}
	
	
	@GetMapping("/{id}/students")
	public ResponseEntity<Page<StudentResponseDto>> getStudentsByClassRoomId(@PathVariable @Positive(message = "Please enter a valid id") int id,
																			 @RequestParam(defaultValue = "0")int page,
																			 @RequestParam(defaultValue = "10")int size) {
		log.info("Request received to get Students for classroomId={}",id);
		Page<StudentResponseDto> studentResponseDtos=classRoomService.getStudents(id,page,size);
		return ResponseEntity.ok(studentResponseDtos);
	}
	
	@GetMapping("/{id}/notices")
	public ResponseEntity<List<NoticeResponseDto>> getNoticesByClassRoomId(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to get Notices for classroomId={}",id);
	List<NoticeResponseDto> noticeResponseDtos=classRoomService.getNotices(id);
		return ResponseEntity.ok(noticeResponseDtos);
	}
	
	@GetMapping("/{id}/teachers")
	public ResponseEntity<List<TeacherResponseDto>> getTeachersByClassRoomId(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to get Teachers for classroomId={}",id);
		List<TeacherResponseDto> teacherResponseDtos=classRoomService.getTeachers(id);
		return ResponseEntity.ok(teacherResponseDtos);
	}
	
	@GetMapping("/{id}/coordinator")
	public ResponseEntity<CoordinatorResponseDto> getCoordinatorByClassRoomId(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to get coordinator for classroomId={}",id);
		CoordinatorResponseDto coordinatorResponseDto=classRoomService.getCoordinator(id);
		return ResponseEntity.ok(coordinatorResponseDto);
	}
	
	@GetMapping("/{id}/notes")
	public ResponseEntity<?> getNotesByClassRoomId(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to get All Notes for classroomId={}",id);
		List<NotesProvidedResponseDto> notesProvideds=classRoomService.getNotesProvided(id);
		return ResponseEntity.ok(notesProvideds);
	}
	
	@GetMapping("/{id}/assignments")
	public ResponseEntity<List<AssignmentResponseDto>> getAssignmentsByClassRoomId(@PathVariable @Positive(message = "Please enter a valid id") int id) {
		log.info("Request received to get all Assignments issued for classroomId={}",id);
		List<AssignmentResponseDto> assignmentResponseDtos=classRoomService.getAssignments(id);
		return ResponseEntity.ok(assignmentResponseDtos);
	}
	
	@GetMapping("{classroomId}/courses")
	public ResponseEntity<List<CourseResponseDto>> getCourses(@PathVariable @Positive(message = "Please enter a valid id") int classroomId) {
		log.info("Request received to get courses for classroomId={}",classroomId);
		List<CourseResponseDto> courseResponseDto= classRoomService.getCourses(classroomId);
		return ResponseEntity.ok(courseResponseDto);
	}
	
	
	
	

}
