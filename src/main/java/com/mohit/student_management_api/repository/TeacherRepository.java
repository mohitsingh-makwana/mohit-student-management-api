package com.mohit.student_management_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.student_management_api.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

	Teacher findByEmail(String email);

}
