package com.mohit.student_management_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.student_management_api.entity.NotesProvided;

@Repository
public interface NotesProvidedRepository extends JpaRepository<NotesProvided, Integer>{

	public List<NotesProvided> findByClassroomIdAndCourseId(Integer classroomId,String courseId);
}
