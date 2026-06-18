package com.mohit.student_management_api.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter  extends OncePerRequestFilter{
	
	private final JwtHelper jwtHelper;
	private final CustomUserDetailsService  customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header=request.getHeader("Authorization");
		
		String token=null;
		String email=null;
		
		if(header!=null && header.startsWith("Bearer ")) {
			token=header.substring(7);
			email=jwtHelper.extractUsername(token);
		}
		
		if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=customUserDetailsService.loadUserByUsername(email);
			
			if (jwtHelper.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken=
						new UsernamePasswordAuthenticationToken(email,null, userDetails.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
