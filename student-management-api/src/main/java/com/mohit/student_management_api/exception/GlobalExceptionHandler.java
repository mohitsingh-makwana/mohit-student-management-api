package com.mohit.student_management_api.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mohit.student_management_api.dto.ErrorResponseDto;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex){
		log.error("Validation Exception occured..");
		Map<String , String> errors=new HashMap<>();
				ex.getBindingResult()
				.getFieldErrors()
				.forEach(error->errors
						.put(error.getField(), error.getDefaultMessage()));
		
		return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex){
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
								ErrorResponseDto.builder()
								.localDateTime(LocalDateTime.now())
								.status(HttpStatus.NOT_FOUND.value())
								.message(ex.getMessage())
								.build()
								);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException ex){
		
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				ErrorResponseDto.builder()
				.localDateTime(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.message(ex.getMessage())
				.build()
				);
		}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(ConstraintViolationException ex){
		
		return ResponseEntity.badRequest()
				.body(
						ErrorResponseDto.builder()
						.status(HttpStatus.BAD_REQUEST.value())
						.message(ex.getMessage())
						.localDateTime(LocalDateTime.now())
						.build()
					);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleRemainingException(Exception ex){
		
		log.error("Unexpected exception occurred..");
		return ResponseEntity.badRequest()
				.body(ErrorResponseDto.builder()
						.localDateTime(LocalDateTime.now())
						.status(HttpStatus.BAD_REQUEST.value())
						.message(ex.getMessage())
						.build());
	}
	

}
