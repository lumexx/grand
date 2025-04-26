package com.example.grand.user.service;

import com.example.grand.user.domain.User;
import com.example.grand.user.dto.UserDto;
import com.example.grand.user.dto.request.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    Page<UserDto> findAll(UserFilter filter, Pageable pageable);

    User getByEmail(String email);

    UserDetailsService userDetailsService();

}
