package com.example.grand.user.service.impl;

import com.example.grand.common.Constant;
import com.example.grand.common.ExceptionType;
import com.example.grand.common.exception.RestAPIException;
import com.example.grand.user.domain.User;
import com.example.grand.user.dto.UserDto;
import com.example.grand.user.dto.converter.UserConverter;
import com.example.grand.user.dto.request.UserFilter;
import com.example.grand.user.repository.UserRepository;
import com.example.grand.user.service.UserService;
import com.example.grand.user.specification.UserSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserSpecificationBuilder userSpecificationBuilder;
    private final UserConverter userConverter;

    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> findAll(UserFilter filter, Pageable pageable) {
        return userRepository.findAll(userSpecificationBuilder.buildSpecification(filter), pageable)
                .map(userConverter::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByEmail(String username) {
        List<String> emails = List.of(username.split(Constant.USERNAME_SPLIT_SYMBOL));
        return userRepository.findByEmailIn(emails)
                .orElseThrow(() -> new RestAPIException(ExceptionType.USER_NOT_FOUND));

    }

    public UserDetailsService userDetailsService() {
        return this::getByEmail;
    }

}
