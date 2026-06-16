package com.mohit.student_management_api.service;

import java.io.IOException;
import java.lang.System.Logger;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mohit.student_management_api.dto.NoticeRequestDto;
import com.mohit.student_management_api.entity.ClassRoom;
import com.mohit.student_management_api.entity.Notice;
import com.mohit.student_management_api.exception.ResourceNotFoundException;
import com.mohit.student_management_api.repository.ClassRoomRepository;
import com.mohit.student_management_api.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeService {
	
	private final NoticeRepository noticeRepository;
	private final ClassRoomRepository classRoomRepository;
	private final FileValidationService fileValidationService;
	

	public void createNotice(NoticeRequestDto noticeRequestDto) {
		int classroomId=noticeRequestDto.getClassroomId();
	ClassRoom classroom=classRoomRepository.findById(classroomId).orElseThrow(() -> new ResourceNotFoundException("Classroom not found in NoticeService with id="+classroomId));
		
		Notice notice=Notice.builder()
				.topic(noticeRequestDto.getTopic())
				.noticeDoc(noticeRequestDto.getNoticeDoc().getOriginalFilename())
				.issuedAt(LocalDateTime.now())
				.lastDate(noticeRequestDto.getLastDate())
				.classroom(classroom)
				.build();
		
	
			saveNotice(noticeRequestDto.getNoticeDoc());
			log.info("Creating notice with classroomId={} ",noticeRequestDto.getClassroomId());

		noticeRepository.save(notice);
		log.info("Notice created successfully with classroomId={} ",noticeRequestDto.getClassroomId());

	}

	public void deleteNotice(int id) {
		Notice notice=noticeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notice not found with id="+id));
		log.info("Deleting notice with noticeId={} ",id);

		noticeRepository.delete(notice);
		log.info("Notice deleted successfully with classroomId={} ",id);

		
	}

	public void updateNotice(int id, LocalDate lastDate, MultipartFile noticeDoc) {
		Notice notice=noticeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notice Not Found for updation "+id));
		
		if(lastDate!=null) {
			notice.setLastDate(lastDate);
		}
		if(noticeDoc!=null) {
			notice.setNoticeDoc("/notices/"+noticeDoc.getOriginalFilename());
		}
		log.info("Updating notice with noticeId={} ",id);
		saveNotice(noticeDoc);
		noticeRepository.save(notice);
		log.info("Notice updated successfully with noticeId={} ",id);

		
	}
	
	public void saveNotice(MultipartFile file) {
		log.info("Saving notice file {} in folder",file.getOriginalFilename());

		//Validation
		fileValidationService.checkPdfOrImageFile(file);
		
		String fileName=file.getOriginalFilename();
		
		Path path=Paths.get("C:/Users/Asus/Desktop/DemoImageFolder");
		if(!Files.exists(path))
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
			log.error(" Directory not exist");
			}
		
		Path filePath=path.resolve(fileName);
		
		try {
			Files.copy(file.getInputStream(), filePath,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			log.error("file {} not saved ",fileName);
		}
		log.info(" File {} saved successfully",fileName);
	}

}
