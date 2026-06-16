package com.mohit.student_management_api.dto;

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
public class MarksResponseDto {

	Integer rollNo;
	Double marks;
	String examName;
	String studentName;
	String className;
	String courseName;
}
