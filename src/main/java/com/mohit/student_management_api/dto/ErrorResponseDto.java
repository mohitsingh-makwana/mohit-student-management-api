package com.mohit.student_management_api.dto;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponseDto {

	LocalDateTime localDateTime;
	
	int status;
	
	String message;
	
}
