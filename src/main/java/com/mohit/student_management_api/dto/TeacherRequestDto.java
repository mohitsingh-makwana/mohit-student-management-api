package com.mohit.student_management_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class TeacherRequestDto {
	@NotNull(message = "Teacher id must be required")
	@Positive(message = "Id must be positive")
	Integer id;
	
	@NotBlank(message = "Name must be required")
	String name;
	
	@Email(message = "Please enter a valid email")
	@NotEmpty(message = "Email must be required")
	String email;
	
	@Size(min = 10, max = 10 ,message = "Please enter a valid contact no")
	@NotBlank(message = "Contact no must be required")
	String contactNo;
	
	@NotNull(message = "Department Id must be required")
	@Positive(message = "Id must be positive")
	Integer departmentId;

}
