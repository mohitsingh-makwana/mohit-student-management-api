package com.mohit.student_management_api.mapper;

import org.springframework.stereotype.Component;

import com.mohit.student_management_api.dto.AssignmentUploadResponseDto;
import com.mohit.student_management_api.entity.AssignmentUploaded;

@Component
public class AssignmentUploadMapper {

	public AssignmentUploadResponseDto toAssignmentUploadResponseDto(AssignmentUploaded assignmentUploaded) {
		return AssignmentUploadResponseDto.builder()
				.studentName(assignmentUploaded.getStudent().getName())
				.courseName(assignmentUploaded.getAssignment().getCourse().getCourseName())
				.assignmentStatus(assignmentUploaded.getAssignmentStatus())
				.assignmentUploadPath(assignmentUploaded.getAssignmentUploadFilePath())
				.build();
	}
	
}
