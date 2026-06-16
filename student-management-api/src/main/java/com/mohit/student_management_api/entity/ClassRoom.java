package com.mohit.student_management_api.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name="classroom")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level=AccessLevel.PRIVATE)
public class ClassRoom {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	@Column
	String name;
	
	@ManyToOne
	@JoinColumn(name="dept_id")
	Department department;
	
	@OneToMany(mappedBy="classroom",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	List<Student> students=new ArrayList<>();
	
	@OneToMany(mappedBy="classroom",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	List<Notice> notices=new ArrayList<>();
	
	@OneToMany(mappedBy="classroom",fetch = FetchType.LAZY)
	List<NotesProvided> notesProvideds=new ArrayList<>();
	
	@OneToMany(mappedBy="classroom",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	List<AssignmentIssued> assignments=new ArrayList<>();
	
	
	
	@ManyToOne
	@JoinColumn(name="coordinator_id")
	Coordinator coordinator;
	
	@OneToMany(mappedBy="classroom",fetch = FetchType.EAGER)
	List<Course> courses=new ArrayList<>();
}
