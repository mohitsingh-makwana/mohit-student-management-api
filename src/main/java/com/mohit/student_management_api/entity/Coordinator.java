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
@Table(name="coordinator")
@FieldDefaults(level=AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coordinator {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 int cId;

	@Column
	String name;
	
	@Column(unique = true,nullable = false)
	String email;
	
	@Column(nullable = false)
	String password;
	
	
	@Column(unique = true,nullable = false)
	String contactNo;
	
	@ManyToOne
	@JoinColumn(name="dept_id")
	Department department;
	
	@OneToMany(mappedBy="coordinator" ,fetch =FetchType.LAZY)
	List<Student> students=new ArrayList<>();	
	
	@OneToMany(mappedBy="coordinator",fetch = FetchType.EAGER)
	List<ClassRoom> classrooms=new ArrayList<>();
}
