package com.mohit.student_management_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mohit.student_management_api.dto.MarksRequestDto;
import com.mohit.student_management_api.dto.MarksResponseDto;
import com.mohit.student_management_api.entity.Course;
import com.mohit.student_management_api.entity.Marks;
import com.mohit.student_management_api.entity.Student;
import com.mohit.student_management_api.entity.Teacher;
import com.mohit.student_management_api.exception.ResourceNotFoundException;
import com.mohit.student_management_api.repository.CourseRepository;
import com.mohit.student_management_api.repository.MarksRepository;
import com.mohit.student_management_api.repository.StudentRepository;
import com.mohit.student_management_api.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MarksService {

	private final MarksRepository marksRepository;
	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;

	public void uploadMarks(MarksRequestDto marksRequestDto) {
		String courseId=marksRequestDto.getCourseId();
		int studentId=marksRequestDto.getStudentId();
		
		Course course=courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course Not Found in Marks Service with id="+courseId));
		Student student=studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student Not Found in Marks Service with id="+studentId));

		Marks mark=Marks.builder()	
				.marks(marksRequestDto.getMarks())
				.examName(marksRequestDto.getExamName())
				.resultStatus(marksRequestDto.getResultStatus())
				.course(course)
				.student(student)
				.build();
		log.info("Uploading Marks for studentId={} and courseId={}",marksRequestDto.getStudentId(),marksRequestDto.getCourseId());
		marksRepository.save(mark);
		log.info("Marks uploaded successfully for studentId={} and courseId={}",marksRequestDto.getStudentId(),marksRequestDto.getCourseId());

	}

	public List<MarksResponseDto> getStudentMarks(String courseId, int studentId) {
		log.info("Fetching Marks for studentId={} and courseId={}",studentId,courseId);

		List<Marks> marks=marksRepository.findByStudentRollNoAndCourseId(studentId, courseId);
		log.info("Marks fetched for studentId={} and courseId={}",studentId,courseId);
		
		return marks.stream()
				.map(mark->MarksResponseDto.builder()
						.marks(mark.getMarks())
						.examName(mark.getExamName())
						.rollNo(mark.getStudent().getRollNo())
						.studentName(mark.getStudent().getName())
						.courseName(mark.getCourse().getCourseName())
						.className(mark.getStudent().getClassroom().getName())
						.build()).toList();
	}
}
