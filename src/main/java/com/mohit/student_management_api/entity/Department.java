package com.mohit.student_management_api.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
@Table(name="department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Department {
	
	@Id
	@Column(unique = true)
	Integer id;
	
	@Column(unique = true,nullable = false)
	String name;
	
	@OneToMany(mappedBy="department",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	List<Student> students=new ArrayList<>();
	
	@OneToMany(mappedBy="department",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	List<Coordinator> coordinators=new ArrayList<>();
	
	@OneToMany(mappedBy="department",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	List<Course> courses=new ArrayList<>();
	
	@OneToMany(mappedBy="department",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	List<Teacher> teachers=new ArrayList<>();
	
	@OneToMany(mappedBy="department",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	List<ClassRoom> classrooms=new ArrayList<>();
	
	
}
