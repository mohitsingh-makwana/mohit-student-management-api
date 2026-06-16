package com.mohit.student_management_api.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level =AccessLevel.PRIVATE )
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponseDto {

	String id;
	String name;
	String teacherName;
	String departmentName;
	String classroomName;
	
}
