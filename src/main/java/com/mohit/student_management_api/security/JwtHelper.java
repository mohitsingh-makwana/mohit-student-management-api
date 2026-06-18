package com.mohit.student_management_api.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelper {

	private static final String SECRET_KEY="ndohdoihoHDOHOIQHDFFOIB82973242365@#%^%#JDLKD";
	
	private SecretKey getSignKey() {
		//byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims=new HashMap<>();
		claims.put("role", userDetails.getAuthorities().iterator().next().getAuthority());
		return createToken(claims,userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String username) {
		
		return Jwts.builder()
				.subject(username)
				.claims(claims)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+1000*60*60))
				.signWith(getSignKey())
				.compact();
	}
	
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public boolean validateToken(String token,UserDetails userDetails) {
		String username=extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
	
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
	  Claims claims=extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSignKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	public String extractRole(String token) {
		
		Claims claims=extractAllClaims(token);
		return claims.get("role",String.class);
	}

	
}
