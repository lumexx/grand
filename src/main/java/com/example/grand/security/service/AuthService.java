package com.example.grand.security.service;

import com.example.grand.security.dto.request.SignInDto;
import com.example.grand.security.dto.response.TokenResponseDto;

public interface AuthService {
    TokenResponseDto logIn(SignInDto dto);

}
