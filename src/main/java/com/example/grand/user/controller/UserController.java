package com.example.grand.user.controller;

import com.example.grand.common.Routes;
import com.example.grand.user.dto.UserDto;
import com.example.grand.user.dto.request.UserFilter;
import com.example.grand.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping(Routes.USERS)
    public Page<UserDto> getAll(@ParameterObject @Valid UserFilter filter, @ParameterObject Pageable pageable) {
        return service.findAll(filter, pageable);
    }

}
