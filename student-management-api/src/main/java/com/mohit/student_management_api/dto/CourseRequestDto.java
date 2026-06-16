package com.mohit.student_management_api.dto;


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
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequestDto {

	@NotBlank(message = "Course id is required")
	String id;
	
	@NotBlank(message =  "Course name must be required")
	String name;
	
	@NotNull(message = "Department id must be required")
	@Positive(message = "Id must be positive")
	Integer departmentId;
	
	@NotNull(message = "teacher id must be required")
	@Positive(message = "Id must be positive")
	Integer teacherId;
	
	@NotNull(message = "classroom id must be required")
	@Positive(message = "Id must be positive")
	Integer classroomId;
}
