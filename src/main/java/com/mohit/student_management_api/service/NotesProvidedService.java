package com.mohit.student_management_api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mohit.student_management_api.dto.NotesProvidedRequestDto;
import com.mohit.student_management_api.dto.NotesProvidedResponseDto;
import com.mohit.student_management_api.entity.ClassRoom;
import com.mohit.student_management_api.entity.Course;
import com.mohit.student_management_api.entity.NotesProvided;
import com.mohit.student_management_api.entity.Teacher;
import com.mohit.student_management_api.exception.ResourceNotFoundException;
import com.mohit.student_management_api.repository.ClassRoomRepository;
import com.mohit.student_management_api.repository.CourseRepository;
import com.mohit.student_management_api.repository.NotesProvidedRepository;
import com.mohit.student_management_api.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotesProvidedService {
	
	private final NotesProvidedRepository notesProvidedRepository;
	private final TeacherRepository teacherRepository;
	private final CourseRepository courseRepository;
	private final ClassRoomRepository classRoomRepository;
	private final FileValidationService fileValidationService;
	

	
	public void uploadNotes(NotesProvidedRequestDto notesProvidedRequestDto) throws IOException {
		int teacherId=notesProvidedRequestDto.getTeacherId();
		String courseId=notesProvidedRequestDto.getCourseId();
		int classroomId=notesProvidedRequestDto.getClassroomId();
		
		Teacher teacher=teacherRepository.findById(teacherId)
				.orElseThrow(() -> new ResourceNotFoundException("teacher not found in NotesService with id="+teacherId));
		Course course=courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found in NotesService with id="+courseId));
		ClassRoom classRoom=classRoomRepository.findById(classroomId)
				.orElseThrow(() -> new ResourceNotFoundException("ClassRoom not found in NotesService with id="+classroomId));
		
		NotesProvided notesProvided=NotesProvided.builder()
				.notesTopic(notesProvidedRequestDto.getNotesTopic())
				.notesFilePath(notesProvidedRequestDto.getNotesFilePath().getOriginalFilename())
				.notesProvidedAt(LocalDateTime.now())
				.teacher(teacher)
				.classroom(classRoom)
				.course(course)
				.build();
		log.info("Uploading notes for classroomId={} and courseId={}",classroomId,courseId);
		
		saveNotes(notesProvidedRequestDto.getNotesFilePath());
		
		notesProvidedRepository.save(notesProvided);
		log.info("Notes uploaded for classroomId={} and courseId={}",classroomId,courseId);
		
	}

	private void saveNotes(MultipartFile notesFilePath)  {
		log.info("Saving notes file {} in folder",notesFilePath.getOriginalFilename());
		
		//Validation
		fileValidationService.checkPdfFile(notesFilePath);
		
		String fileName=notesFilePath.getOriginalFilename();
		
		Path path=Paths.get("C:/Users/Asus/Desktop/DemoImageFolder",fileName);
		
		try {
			Files.copy(notesFilePath.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		log.info("file {} saved successfully",fileName);
	}

	public List<NotesProvidedResponseDto> getNotes(int classroomId, String courseId) {
		log.info("Fetching notes with classroomId={} and courseId={}",classroomId,courseId);

		List<NotesProvided> list=notesProvidedRepository.findByClassroomIdAndCourseId(classroomId, courseId);
		log.info("Fetched {} notes with classroomId={} and courseId={}",list.size(), classroomId,courseId );

		
		return list.stream()
				.map(notes->NotesProvidedResponseDto.builder()
						.notesTopic(notes.getNotesTopic())
						.notesFilePath(notes.getNotesFilePath())
						.notesProvidedAt(notes.getNotesProvidedAt())
						.courseName(notes.getCourse().getCourseName())
						.build()).toList();
				
	}

}
