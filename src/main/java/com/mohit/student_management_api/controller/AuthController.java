package com.mohit.student_management_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.student_management_api.security.JwtHelper;
import com.mohit.student_management_api.security.JwtRequest;
import com.mohit.student_management_api.security.JwtResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@Valid @RequestBody JwtRequest jwtRequest){
	
	Authentication authentication=authenticationManager.authenticate(
											new UsernamePasswordAuthenticationToken(
													jwtRequest.getEmail(),
													jwtRequest.getPassword()));
		
		UserDetails userDetails=(UserDetails)authentication.getPrincipal();
		
		//We must have to use userDetails for generating token instead of username only
		String token=jwtHelper.generateToken(userDetails);
		
		JwtResponse jwtResponse =JwtResponse.builder()
											.token(token)
											.build();
		return ResponseEntity.ok(jwtResponse);
	}

}
