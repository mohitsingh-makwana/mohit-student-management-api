package com.mohit.student_management_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.student_management_api.entity.AssignmentIssued;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentIssued, Integer>{
	
	public List<AssignmentIssued>findByClassroomIdAndTeacherId(Integer classroom,Integer teacherId); 

}
