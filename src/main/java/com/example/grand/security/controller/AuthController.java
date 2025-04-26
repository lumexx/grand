package com.example.grand.security.controller;

import com.example.grand.common.Routes;
import com.example.grand.security.dto.request.SignInDto;
import com.example.grand.security.dto.response.TokenResponseDto;
import com.example.grand.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Log in")
    @PostMapping(Routes.LOGIN)
    public TokenResponseDto signInStart(@Valid @RequestBody SignInDto signInBody) {
        return authService.logIn(signInBody);
    }

}
