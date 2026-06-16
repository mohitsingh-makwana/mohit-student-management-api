package com.mohit.student_management_api.dto;



import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NoticeRequestDto {

	@NotBlank(message = "Notice topic must be required")
	String topic;
	
	@NotNull(message = "Please attach notice document")
	MultipartFile noticeDoc;
	
	@NotNull(message = "Classroom id is required")
	@Positive(message = "Id must be positive")
	Integer classroomId;
	
	@FutureOrPresent(message = "Please enter valid date")
	@NotNull(message = "please enter the last date")
	LocalDate lastDate;
}
