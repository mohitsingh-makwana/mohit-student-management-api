package com.mohit.student_management_api.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mohit.student_management_api.entity.Coordinator;
import com.mohit.student_management_api.entity.Student;
import com.mohit.student_management_api.entity.Teacher;
import com.mohit.student_management_api.repository.CoordinatorRepository;
import com.mohit.student_management_api.repository.StudentRepository;
import com.mohit.student_management_api.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;
	private final CoordinatorRepository coordinatorRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Student student=studentRepository.findByEmail(email);
		
		if(student!=null) {
			return new CustomUserDetails(
					student.getEmail(),
					student.getPassword(),
					List.of(new SimpleGrantedAuthority("STUDENT"))
					);
		}
		
		Teacher teacher=teacherRepository.findByEmail(email);
		if(teacher!=null) {
			return new CustomUserDetails(
					teacher.getEmail(),
					teacher.getPassword(),
					List.of(new SimpleGrantedAuthority("TEACHER")));	
		}
		Coordinator coordinator=coordinatorRepository.findByEmail(email);
		if(coordinator!=null) {
			return new CustomUserDetails(
					coordinator.getEmail(),
					coordinator.getPassword(),
					List.of(new SimpleGrantedAuthority("COORDINATOR")));
		}
		
		throw new UsernameNotFoundException("User not found");

	}
	
	

}
