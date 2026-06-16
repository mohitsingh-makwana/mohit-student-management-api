package com.mohit.student_management_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.student_management_api.entity.ClassRoom;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Integer>{

}
