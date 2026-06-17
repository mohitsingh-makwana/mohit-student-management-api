package com.mohit.student_management_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.student_management_api.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	Page<Student> getStudentsByDepartmentId(int departmentId, Pageable pageable);
	

}
