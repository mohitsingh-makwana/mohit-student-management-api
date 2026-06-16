package com.mohit.student_management_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.student_management_api.entity.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer>{

}
