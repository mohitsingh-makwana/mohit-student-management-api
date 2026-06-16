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
public class ClassRoomRequestDto {

	@NotBlank(message = "Name is required")
	String name;
	
	@NotNull(message = "Department id is required")
	@Positive(message = "Id must be positive")
	Integer departmentId;
	
	@NotNull(message="Coordinator id is required")
	@Positive(message = "Id must be positive")
	Integer coordinatorId;
}
