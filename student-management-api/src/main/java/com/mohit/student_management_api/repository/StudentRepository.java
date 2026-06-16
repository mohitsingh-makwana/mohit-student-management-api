package com.mohit.student_management_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.student_management_api.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	

}
