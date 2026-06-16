package com.mohit.student_management_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentRequestDto {
	
	@NotNull(message = "Department id must be required")
	@Positive(message = "Id must be positive")
	Integer id;
	
	@NotBlank(message = "Department name must be required")
	String name;

}
