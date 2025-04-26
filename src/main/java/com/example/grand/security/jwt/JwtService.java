package com.example.grand.security.jwt;


import com.example.grand.user.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

	String generateToken(UserDetails userData);

	String extractUserName(String token);

	boolean isTokenValid(String token);

	User getUser();

}
