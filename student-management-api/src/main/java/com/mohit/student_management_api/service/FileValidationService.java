package com.mohit.student_management_api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class FileValidationService {
	
	public void checkPdfFile(MultipartFile file) {
		log.info("Checking File validation");
		if(file==null || file.isEmpty()) {
		log.error("File is empty", new  IllegalArgumentException("File is required"));
		}
		if(!"application/pdf".equals(file.getContentType())) {
			log.error("Invalid file type {}",file.getContentType(), new IllegalArgumentException("Only Pdf file allowed"));
		}
		if(file.getSize()> 5 * 1024 * 1024) {
			log.error("File size exceeds", new IllegalArgumentException("Max File size is 5MB"));
		}
		log.info("File validation done successfully..");
	}
	
	public void checkPdfOrImageFile(MultipartFile file) {
		log.info("Checking File validation");
		if(file==null || file.isEmpty()) {
			log.error("File is empty", new  IllegalArgumentException("File is required"));
		}
		if(!"application/pdf".equals(file.getContentType()) || !"image/png".equals(file.getContentType()) || !"image/jpg".equals(file.getContentType())) {
			log.error("Invalid file type {}",file.getContentType(), new IllegalArgumentException("Only Pdf file allowed"));
			}
		if(file.getSize()> 5 * 1024 * 1024) {
			log.error("File size exceeds", new IllegalArgumentException("Max File size is 5MB"));
		}
		log.info("File validation done successfully..");
	}

}
