package com.mohit.student_management_api.dto;

import com.mohit.student_management_api.enums.AssignmentStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AssignmentUploadResponseDto {
	AssignmentStatus assignmentStatus;
	String assignmentUploadPath;
	Integer rollNo;
	String studentName; 
	String courseName;

}
