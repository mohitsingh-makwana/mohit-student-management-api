package com.mohit.student_management_api.entity;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
@Table(name="course_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Builder
public class Course {

	@Id
	@Column(unique = true,nullable = false)
	String id;
	
	@Column(unique = true)
	String courseName;
	
	@ManyToOne
	@JoinColumn(name="dept_id")
	Department department;
	
	@ManyToMany(mappedBy="courses",fetch = FetchType.LAZY)
	List<Student> students=new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="teacher_id")
	Teacher teacher;
	
	@OneToMany(mappedBy="course",fetch = FetchType.LAZY)
	List<Marks> marks=new ArrayList<>();

	@OneToMany(mappedBy="course",fetch = FetchType.LAZY)
	List<AssignmentIssued> assignments=new ArrayList<>();
	
	
	@ManyToOne
	@JoinColumn(name="classroom_id")
	ClassRoom classroom;
	
}
