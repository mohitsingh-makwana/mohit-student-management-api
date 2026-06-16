package com.mohit.student_management_api.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class AssignmentUploadRequestDto {
	
	@NotNull(message = "Please attach an assignment doc")
	MultipartFile uploadFilePath;
	
	@NotNull(message="Student Roll No is required")
	@Positive(message = "Id must be positive")
	Integer studentId;
	
	@NotNull(message="Assignment id is required")
	@Positive(message = "Id must be positive")
	Integer assignmentIssuedId;
}
