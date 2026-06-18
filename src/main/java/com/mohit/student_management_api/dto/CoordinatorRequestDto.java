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
public class CoordinatorRequestDto {

	@NotBlank(message = "Name is required")
	String name;
	
	@Email(message="Please enter valid email")
	String email;
	
	@NotBlank(message = "Password required")
	@Size(min = 6,message = "Weak Password")
	String password;
	
	@Size(min=10,max=10)
	@NotBlank(message = "Contact No. is required")
	String contactNo;
	
	@NotNull(message = "Department id is required")
	@Positive(message = "Id must be positive")
	Integer departmentId;
	
}
