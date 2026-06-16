package com.mohit.student_management_api.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AssignmentRequestDto {
	@NotBlank(message="Please enter assignment topic")
	String assignmentTopic;
	
	@NotNull(message = "Please attach file")
	MultipartFile assignmentFilePath;
	
	@Future(message="Please enter future date")
	LocalDate deadline;
	
	@NotBlank(message="Course id is required")
	String courseId;
	
	@NotNull(message="Classroom id is required")
	@Positive(message = "Id must be positive")
	Integer classroomId;
	
	@NotNull(message="Teacher id is required")
	@Positive(message = "Id must be positive")
	Integer teacherId;
}
