package com.mohit.student_management_api.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponseDto {

	Integer rollNo;
	String name;
	String email;
	String contactNo;
	String address;
	Double attendance;
}
