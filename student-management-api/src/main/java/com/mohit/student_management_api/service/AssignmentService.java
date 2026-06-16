package com.mohit.student_management_api.service;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mohit.student_management_api.dto.AssignmentRequestDto;
import com.mohit.student_management_api.dto.AssignmentResponseDto;
import com.mohit.student_management_api.dto.AssignmentUploadResponseDto;
import com.mohit.student_management_api.entity.AssignmentIssued;
import com.mohit.student_management_api.entity.AssignmentUploaded;
import com.mohit.student_management_api.entity.ClassRoom;
import com.mohit.student_management_api.entity.Course;
import com.mohit.student_management_api.entity.Teacher;
import com.mohit.student_management_api.exception.ResourceNotFoundException;
import com.mohit.student_management_api.repository.AssignmentRepository;
import com.mohit.student_management_api.repository.AssignmentUploadsRepository;
import com.mohit.student_management_api.repository.ClassRoomRepository;
import com.mohit.student_management_api.repository.CourseRepository;
import com.mohit.student_management_api.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssignmentService {
	
	private final AssignmentRepository assignmentRepository;
	private final CourseRepository courseRepository;
	private final ClassRoomRepository classRoomRepository;
	private final TeacherRepository teacherRepository;
	private final AssignmentUploadsRepository assignmentUploadsRepository;
	private final FileValidationService fileValidationService;

	public void issueAssignment(AssignmentRequestDto assignmentRequestDto) {
		String courseId=assignmentRequestDto.getCourseId();
		Integer teacherId=assignmentRequestDto.getTeacherId();
		Integer classroomId=assignmentRequestDto.getClassroomId();
		Course course=courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not Found="+courseId));
		Teacher teacher=teacherRepository.findById(teacherId).orElseThrow(() -> new ResourceNotFoundException("Teacher Not Found="+teacherId));
		ClassRoom classRoom=classRoomRepository.findById(classroomId).orElseThrow( () -> new ResourceNotFoundException("Clasroom Not Found with Id="+classroomId));
		
		log.info("Issueing assignment for classroomId={} and courseId={}",classroomId,courseId);
		
		AssignmentIssued assignmentIssued=AssignmentIssued.builder()
				.assigmentTopic(assignmentRequestDto.getAssignmentTopic())
				.assignmentFilePath(assignmentRequestDto.getAssignmentFilePath().getOriginalFilename())
				.issuedAt(LocalDateTime.now())
				.deadline(assignmentRequestDto.getDeadline())
				.classroom(classRoom)
				.course(course)
				.teacher(teacher)
				.build();
		
		
		saveAssignment(assignmentRequestDto.getAssignmentFilePath());
		assignmentRepository.save(assignmentIssued);
		log.info("Assignment Issued successfully for classroomId={} and courseId={}",classroomId,courseId);
	}

	private void saveAssignment(MultipartFile assignmentFilePath) {
		//Validation Check
		log.info("file {} sent for validation check",assignmentFilePath.getOriginalFilename());
		fileValidationService.checkPdfOrImageFile(assignmentFilePath);
		log.info("saving file {} to folder",assignmentFilePath.getOriginalFilename());
		
		String fileName=assignmentFilePath.getOriginalFilename();
		
		Path path=Paths.get("file:C:/Users/Asus/Desktop/DemoImageFolder/assignment",fileName);
		try {
			Files.copy(assignmentFilePath.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			log.error("file {} not saved....",fileName);
		}
		log.info("file {} saved successfully",fileName);
	}

	public List<AssignmentResponseDto> getAssignments(int classroomId, int teacherId) {
		log.info("fetching assignments for classroomId={}  and teacherId={}",classroomId,teacherId);
	List<AssignmentIssued> list=assignmentRepository.findByClassroomIdAndTeacherId(classroomId, teacherId);
	log.info("Fetched {} assignments for classroomId={} and teacherId={}",list.size(),classroomId ,teacherId);
		return list.stream()
				.map(assignment->AssignmentResponseDto.builder()
						.assignmentTopic(assignment.getAssigmentTopic())
						.assignmentFilePath(assignment.getAssignmentFilePath())
						.deadline(assignment.getDeadline())
						.build()).toList();
	}

	public List<AssignmentUploadResponseDto> getUploadedAssignment(int id) {
		log.info("fetching uploaded assignments for assignmentIssuedId={}",id);
		List<AssignmentUploaded> assignmentUploadeds=assignmentUploadsRepository.findByAssignmentId(id);
		log.info("Fetched {} uploaded assignments for assignmentIssuedId={}",id);
		return assignmentUploadeds.stream()
				.map(assignmentUploaded->AssignmentUploadResponseDto.builder()
						.assignmentStatus(assignmentUploaded.getAssignmentStatus())
						.assignmentUploadPath(assignmentUploaded.getAssignmentUploadFilePath())
						.studentName(assignmentUploaded.getStudent().getName())
						.rollNo(assignmentUploaded.getStudent().getRollNo())
						.build()).toList();
	}

}
