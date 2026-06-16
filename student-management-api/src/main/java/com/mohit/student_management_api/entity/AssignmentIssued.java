package com.mohit.student_management_api.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="assignment_issued")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level=AccessLevel.PRIVATE)
public class AssignmentIssued {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	@Column(nullable = false)
	String assigmentTopic;
	
	@Column(nullable=false)
	String assignmentFilePath;
	
	@Column
	LocalDateTime issuedAt;
	
	@Column
	LocalDate deadline;
	
	@ManyToOne
	@JoinColumn(name="classroom_id")
	ClassRoom classroom;
	
	@ManyToOne
	@JoinColumn(name="teacher_id")
	Teacher teacher;
	
	@OneToMany(mappedBy="assignment",fetch = FetchType.LAZY)
	List<AssignmentUploaded> assignmentUploads;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	Course course;
}
