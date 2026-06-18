package com.mohit.student_management_api.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.mohit.student_management_api.enums.FeeStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name="student_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Student {
	
	@Id
	@Column(unique = true,nullable = false)
	Integer rollNo;
	
	@Column(nullable = false)
	String name;
	
	@Column
	 Date dob;
	
	@Column(unique = true,nullable = false)
	String email;
	
	@Column(nullable = false)
	String password;
	
	@Column(unique = true)
	String contactNo;
	
	@Column
	String address;
	
	@Column
	double attendance=0;
	
	@Enumerated(EnumType.STRING)
	@Column
	FeeStatus feeStatus=FeeStatus.DUE;
	
	@Column
	double feeDue=0;
	
	@ManyToOne
	@JoinColumn(name="dept_id")
	Department department;
	
	@ManyToOne
	@JoinColumn(name="coordinator_id")
	Coordinator coordinator;
	
	@ManyToOne
	@JoinColumn(name="class_id")
	ClassRoom classroom;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="student_courses" ,
			joinColumns=@JoinColumn(name="student_id"),
			inverseJoinColumns=@JoinColumn(name="course_id"))
	List<Course> courses=new ArrayList<>();
	

	@OneToMany(mappedBy="student")
	List<AssignmentUploaded> assignmentUploaded=new ArrayList<>();
	
	@OneToMany(mappedBy="student")
	List<Marks> marks=new ArrayList<>();

	
	
	
	

}
