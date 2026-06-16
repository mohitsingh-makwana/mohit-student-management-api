package com.mohit.student_management_api.dto;

import com.mohit.student_management_api.enums.FeeStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRequestDto {
	@NotNull(message = "Please enter roll no")
	@Positive(message = "Enter a valid Roll no")
	Integer rollNo;
	
	@NotBlank(message="Please enter name")
	@Size(min = 3,max = 20)
	String name;
	
	@Email(message = "Please enter a valid email")
	@NotBlank(message = "Email must be required")
	String email;
	
	@NotBlank(message = "Address must be required")
	String address;
	
	@Size(min=10,max = 10,message = "Please enter a valid contact no")
	@NotBlank(message = "Contact no must be required")
	String contactNo;
	
	@Min(0)
	@Max(100)
	Double attendance;
	
	FeeStatus feeStatus;
	
	@NotNull(message = "Enter a Due fee amount")
	Double dueFee;
	
	@NotNull(message = "Department Id must be required")
	@Positive(message = "Id must be positive")
	Integer departmentId;
	@NotNull(message = "Corrdinator Id must be required")
	@Positive(message = "Id must be positive")
	Integer coordinatorId;
	
	@NotNull(message = "Classroom Id must be required")
	@Positive(message = "Id must be positive")
	Integer classroomId;

}
