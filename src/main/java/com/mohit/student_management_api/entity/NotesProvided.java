package com.mohit.student_management_api.entity;



import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="notes_provided")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level=AccessLevel.PRIVATE)
public class NotesProvided {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	@Column(nullable = false)
	String notesTopic;
	
	@Column(nullable = false)
	String notesFilePath;
	
	@Column
	LocalDateTime notesProvidedAt;
	 
	@ManyToOne
	@JoinColumn(name="teacher_id")
	Teacher teacher;

	@ManyToOne
	@JoinColumn(name="course_id")
	Course course;

	@ManyToOne
	@JoinColumn(name="classroom_id")
	ClassRoom classroom;
	
}
