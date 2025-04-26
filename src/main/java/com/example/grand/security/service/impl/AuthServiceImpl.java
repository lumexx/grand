package com.example.grand.security.service.impl;

import com.example.grand.security.dto.request.SignInDto;
import com.example.grand.security.dto.response.TokenResponseDto;
import com.example.grand.security.jwt.JwtService;
import com.example.grand.security.service.AuthService;
import com.example.grand.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public TokenResponseDto logIn(SignInDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        UserDetails user = userService.userDetailsService().loadUserByUsername(request.getEmail());

        String jwt = jwtService.generateToken(user);

        return new TokenResponseDto(jwt);
    }

}
