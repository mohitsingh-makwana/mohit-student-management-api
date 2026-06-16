package com.mohit.student_management_api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.hibernate.boot.model.source.internal.hbm.FetchProfileBinder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mohit.student_management_api.dto.AssignmentUploadRequestDto;
import com.mohit.student_management_api.dto.AssignmentUploadResponseDto;
import com.mohit.student_management_api.entity.AssignmentIssued;
import com.mohit.student_management_api.entity.AssignmentUploaded;
import com.mohit.student_management_api.entity.Student;
import com.mohit.student_management_api.enums.AssignmentStatus;
import com.mohit.student_management_api.exception.ResourceNotFoundException;
import com.mohit.student_management_api.repository.AssignmentRepository;
import com.mohit.student_management_api.repository.AssignmentUploadsRepository;
import com.mohit.student_management_api.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssignmentUploadService {

	private final AssignmentUploadsRepository assignmentUploadsRepository;
	private final StudentRepository studentRepository;
	private final AssignmentRepository assignmentRepository;
	private final FileValidationService fileValidationService;

	public void uploadAssignment(AssignmentUploadRequestDto assignmentUploadRequestDto) {
		int studentId=assignmentUploadRequestDto.getStudentId();
		int assignmentId=assignmentUploadRequestDto.getAssignmentIssuedId();
		log.info("Uploading  assignments for studentId={}  and assignmentId={}",studentId,assignmentId);
		Student student=studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student not Found with id="+studentId));
		AssignmentIssued assignmentIssued=assignmentRepository.findById(assignmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Assignment not Found with id="+assignmentId));
		
		AssignmentUploaded assignmentUploaded=AssignmentUploaded.builder()
				.assignmentStatus(AssignmentStatus.SUBMITTED)
				.assignmentUploadFilePath(assignmentUploadRequestDto.getUploadFilePath().getOriginalFilename())
				.assignment(assignmentIssued)
				.student(student)
				.build();
		
		saveAssignment(assignmentUploadRequestDto.getUploadFilePath());
		
		assignmentUploadsRepository.save(assignmentUploaded);
		log.info("Assignments uploaded successfully for studentId={}  and assignmentId={}",student,assignmentId);
	}

	private void saveAssignment(MultipartFile uploadFilePath) {
		log.info("Saving file {} into folder",uploadFilePath.getOriginalFilename());
		//Validation
		fileValidationService.checkPdfFile(uploadFilePath);
		
		String fileName=uploadFilePath.getOriginalFilename();
		
		Path path=Paths.get("file:C:/Users/Asus/Desktop/DemoImageFolder/assignment",fileName);
		
		try {
			Files.copy(uploadFilePath.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			log.error("file {} not saved.....",fileName);
		}
		log.info("file {} saved successfully",fileName);
	}

	public List<AssignmentUploadResponseDto> getAssignmentUploadedByAssignmentIssuedIdAndStudentId(
			int assignmentIssuedId, int studentId) {
		
		log.info("Fetching uploaded assignments for studentId={}  and assignmentId={}",studentId,assignmentIssuedId);
		List<AssignmentUploaded> assignmentUploadeds=assignmentUploadsRepository.findByAssignmentIdAndStudentRollNo(assignmentIssuedId, studentId);
		log.info("Fetched {} uploaded assignments for studentId={}  and assignmentId={}",assignmentUploadeds.size(), studentId,assignmentIssuedId);
		return assignmentUploadeds.stream()
				.map(assignmentUploaded->AssignmentUploadResponseDto.builder()
						.assignmentStatus(assignmentUploaded.getAssignmentStatus())
						.assignmentUploadPath(assignmentUploaded.getAssignmentUploadFilePath())
						.studentName(assignmentUploaded.getStudent().getName())
						.rollNo(assignmentUploaded.getStudent().getRollNo())
						.courseName(assignmentUploaded.getAssignment().getCourse().getCourseName())
						.build()).toList();
	}

	/*public List<AssignmentUploadResponseDto> getAssignmentUploadedByCourseIdAndStudentId(String courseId,
			int studentId) {
		List<AssignmentUploaded> assignmentUploadeds=assignmentUploadsRepository.findByCourseIdAndStudentId(courseId, studentId);
		
		return assignmentUploadeds.stream()
				.map(assignmentUploaded->AssignmentUploadResponseDto.builder()
						.assignmentStatus(assignmentUploaded.getAssignmentStatus())
						.assignmentUploadPath(assignmentUploaded.getAssignmentUploadFilePath())
						.studentName(assignmentUploaded.getStudent().getName())
						.rollNo(assignmentUploaded.getStudent().getRollNo())
						.courseName(assignmentUploaded.getAssignmentIssued().getCourse().getCourseName())
						.build()).toList();
	}*/
}
