package com.mohit.student_management_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.student_management_api.entity.ClassRoom;
import com.mohit.student_management_api.entity.Student;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Integer>{

	Page<Student> findStudentsById(int id, Pageable pageable);

	Page<ClassRoom> findClassRoomsByDepartmentId(int departmentId, Pageable pageable);

}
