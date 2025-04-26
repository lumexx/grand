package com.example.grand.email.service;

import com.example.grand.email.dto.EmailDataDto;

import java.util.UUID;

public interface EmailService {
    EmailDataDto create(EmailDataDto dto);

    EmailDataDto update(EmailDataDto dto);

    void delete(UUID emailUuid);

}
