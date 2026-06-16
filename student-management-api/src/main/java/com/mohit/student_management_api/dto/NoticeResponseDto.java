package com.mohit.student_management_api.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class NoticeResponseDto {
	
	String topic;
	String noticeDoc;
	
	LocalDateTime issueDate;
	LocalDate lastDate;

}
