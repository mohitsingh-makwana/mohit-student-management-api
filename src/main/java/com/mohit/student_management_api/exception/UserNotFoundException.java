package com.mohit.student_management_api.exception;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public UserNotFoundException() {
		
	}
	public UserNotFoundException(String s) {
		super(s);
	}
}
