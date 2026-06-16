package com.mohit.student_management_api.dto;
 

import org.springframework.web.multipart.MultipartFile;

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
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class NotesProvidedRequestDto {

	@NotBlank(message = "Please enter notes topic")
	String notesTopic;
	
	@NotNull(message = "Please attach the notes file")
	MultipartFile notesFilePath;
	
	@NotNull(message="Teacher id must be required")
	@Positive(message = "Id must be positive")
	Integer teacherId;
	
	@NotBlank(message = "Course id must be required")
	@Positive(message = "Id must be positive")
	String courseId;
	
	@NotNull(message = "classroom id must  be required")
	@Positive(message = "Id must be positive")
	Integer classroomId;
	
	
}
