package com.mohit.student_management_api.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name="teacher_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Builder
public class Teacher {
	
	@Id
	@Column(unique = true,nullable = false)
	 int id;

	@Column(nullable = false)
	String name;
	
	@Column(unique = true)
	String email;
	
	@Column(unique = true)
	String contactNo;
	
	@ManyToOne
	@JoinColumn(name="dept_id")
	Department department;
	
	@OneToMany(mappedBy="teacher",fetch = FetchType.LAZY)
	List<AssignmentIssued> assignmentIssued=new ArrayList<>();
	
	@OneToMany(mappedBy="teacher")
	List<Course> courses=new ArrayList<>();
	

}
