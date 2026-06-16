package com.mohit.student_management_api.dto;

import com.mohit.student_management_api.enums.ExamResultStatus;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class MarksRequestDto {

	@Min(0)
	@Max(100)
	Double marks;
	
	@NotBlank(message = "Please enter exam name")
	String examName;
	
	
	ExamResultStatus resultStatus;
	
	@NotNull(message = "student roll no must be required")
	@Positive(message = "Id must be positive")
	Integer studentId;
	
	@NotBlank(message = "course id must be required")
	@Positive(message = "Id must be positive")
	String courseId;
	
	
}
