package com.mohit.student_management_api.dto;

import java.time.LocalDate;


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
public class AssignmentResponseDto {

String assignmentTopic;
String assignmentFilePath;

LocalDate deadline;


}
