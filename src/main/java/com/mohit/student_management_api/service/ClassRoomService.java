package com.mohit.student_management_api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mohit.student_management_api.dto.AssignmentResponseDto;
import com.mohit.student_management_api.dto.ClassRoomRequestDto;
import com.mohit.student_management_api.dto.CoordinatorResponseDto;
import com.mohit.student_management_api.dto.CourseResponseDto;
import com.mohit.student_management_api.dto.NotesProvidedResponseDto;
import com.mohit.student_management_api.dto.NoticeResponseDto;
import com.mohit.student_management_api.dto.StudentResponseDto;
import com.mohit.student_management_api.dto.TeacherResponseDto;
import com.mohit.student_management_api.entity.AssignmentIssued;
import com.mohit.student_management_api.entity.ClassRoom;
import com.mohit.student_management_api.entity.Coordinator;
import com.mohit.student_management_api.entity.Course;
import com.mohit.student_management_api.entity.Department;
import com.mohit.student_management_api.entity.NotesProvided;
import com.mohit.student_management_api.entity.Notice;
import com.mohit.student_management_api.entity.Student;

import com.mohit.student_management_api.exception.ResourceNotFoundException;
import com.mohit.student_management_api.mapper.StudentMapper;
import com.mohit.student_management_api.repository.ClassRoomRepository;
import com.mohit.student_management_api.repository.CoordinatorRepository;
import com.mohit.student_management_api.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassRoomService {


	private final ClassRoomRepository classRoomRepository;
	private final DepartmentRepository departmentRepository;
	private final CoordinatorRepository coordinatorRepository;
	private final StudentMapper studentMapper;
    
	
	public ClassRoom getClassRoomObject(int id) {
		return classRoomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ClassRoom Not Found with Id="+id));
		}
	
	public void createClassRoom(ClassRoomRequestDto classRoomRequestDto) {
		
		Department department=departmentRepository.findById(classRoomRequestDto.getDepartmentId())
				.orElseThrow(() -> new RuntimeException("Department Not Found In ClassRoom Service!!"));
		
		Coordinator coordinator=coordinatorRepository.findById(classRoomRequestDto.getCoordinatorId())
				.orElseThrow(() -> new RuntimeException("Coordinator not Found"));
		log.info("Creating  classroom for departmentId={}",classRoomRequestDto.getDepartmentId());
		ClassRoom classRoom=ClassRoom.builder()
				.name(classRoomRequestDto.getName())
				.coordinator(coordinator)
				.department(department)
				.build();
		
		 classRoomRepository.save(classRoom);
		 log.info("Classroom created successfully for departmentId={}",classRoomRequestDto.getDepartmentId());
	}

	public void deleteClassRoom(int id) {
		log.info("Deleting classroom for classroomId={} ",id);
		ClassRoom classRoom=getClassRoomObject(id);
		if(classRoom==null) {
			return ;
		}
		log.info("Classroom deleted successfully for classroomId={}",id);
		classRoomRepository.delete(classRoom);
	}

	public Page<StudentResponseDto> getStudents(int id, int page, int size) {
	
		log.info("Fetching Students for classroomId={}",id);
		Pageable pageable=PageRequest.of(page, size);
		Page<Student> students=classRoomRepository.findStudentsById(id,pageable);
		log.info("Fetched {} students classroomId={}",students.getContent(), id);
		
		return students.map(studentMapper::toStudentResponseDto);
		}

	public List<NoticeResponseDto> getNotices(int id) {
		ClassRoom classRoom=getClassRoomObject(id);
		log.info("Fetching noices for classroomId={}",id);
		List<Notice> notices=classRoom.getNotices();
		log.info("Fetched {} notices for classroomId={}",notices.size(), id);
		List<NoticeResponseDto> noticeResponseDtos=notices.stream().map(notice->NoticeResponseDto.builder()
										.topic(notice.getTopic())
										.noticeDoc(notice.getNoticeDoc())
										.issueDate(notice.getIssuedAt())
										.lastDate(notice.getLastDate())
										.build())
				.toList();
				
				return noticeResponseDtos;
		}

	public List<TeacherResponseDto> getTeachers(int id) {
		ClassRoom classRoom=getClassRoomObject(id);
		log.info("Fetching teachers for classroomId={}",id);
		List<Course> courses=classRoom.getCourses();
		log.info("Fetched {} uploaded assignments for classroomId={}  and assignmentId={}",courses.size(),id);
		
		return courses.stream()
				.map(course->TeacherResponseDto.builder()
						.name(course.getTeacher().getName())
						.email(course.getTeacher().getEmail())
						.contactNo(course.getTeacher().getContactNo())
						.departmentName(course.getDepartment().getName())
						.build()).toList();
	}

	public CoordinatorResponseDto getCoordinator(int id) {
		ClassRoom classRoom=getClassRoomObject(id);
		
		log.info("Fetching coordinator for classroomId={}",id);
	Coordinator coordinator=classRoom.getCoordinator();
	log.info("Fetched 1 coordinator for classroomId={}",id);
		return CoordinatorResponseDto.builder()
				.name(coordinator.getName())
				.email(coordinator.getEmail())
				.contactNo(coordinator.getContactNo())
				.departmentName(coordinator.getDepartment().getName())
				.build();
				
	}

	public List<NotesProvidedResponseDto> getNotesProvided(int id) {
		ClassRoom classRoom=getClassRoomObject(id);
		log.info("Fetching notes for classroomId={}",id);
		List<NotesProvided> notesProvideds=classRoom.getNotesProvideds();
		log.info("Fetched {} notes for classroomId={}",notesProvideds.size(),id);
		return notesProvideds.stream()
				.map(note->NotesProvidedResponseDto.builder()
						.notesTopic(note.getNotesTopic())
						.notesFilePath(note.getNotesFilePath())
						.notesProvidedAt(note.getNotesProvidedAt())
						.build()).toList();
	}

	public List<AssignmentResponseDto> getAssignments(int id) {
		ClassRoom classRoom=getClassRoomObject(id);
		log.info("Fetching issued assignments for classroomId={}",id);
		List<AssignmentIssued> assignmentIssueds=classRoom.getAssignments();
		log.info("Fetched {} assignments for classroomId={}",id);
		
		
		return assignmentIssueds.stream()
				.map(assignment->AssignmentResponseDto.builder()
				.assignmentTopic(assignment.getAssigmentTopic())
				.assignmentFilePath(assignment.getAssignmentFilePath())
				.deadline(assignment.getDeadline())
				.build()).toList();
	}
	
	
	public List<CourseResponseDto> getCourses(int classroomId) {
		ClassRoom classRoom=getClassRoomObject(classroomId);
		log.info("Fetching courses for classroomId={}",classroomId);
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
	 
	 log.info("Fetched {} courses for classroomId={}",classroomId);
	 return courseResponseDtos;
	}

}
