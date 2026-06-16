package com.mohit.student_management_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.student_management_api.entity.AssignmentUploaded;

@Repository
public interface AssignmentUploadsRepository extends JpaRepository<AssignmentUploaded, Integer> {
	
	public List<AssignmentUploaded> findByAssignmentId(Integer assignmentId);
	public List<AssignmentUploaded> findByAssignmentIdAndStudentRollNo(Integer assignmentId,Integer studentRollNo);
//	public List<AssignmentUploaded> findByCourseIdAndStudentId(String courseId,Integer studentId);


}
