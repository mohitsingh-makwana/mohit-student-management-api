package com.mohit.student_management_api.entity;


import com.mohit.student_management_api.enums.ExamResultStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="marks_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Marks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(nullable = false)
	double marks;
	
	@Column
	String examName;
	
	@Enumerated(EnumType.STRING)
	@Column
	ExamResultStatus resultStatus;
	
	@ManyToOne
	@JoinColumn(name="student_roll")
	Student student;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	Course course;
	
}
