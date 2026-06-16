package com.mohit.student_management_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.student_management_api.entity.Marks;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Integer>{

	List<Marks> findByStudentRollNoAndCourseId(
	        int rollNo,
	        String courseId);
}
