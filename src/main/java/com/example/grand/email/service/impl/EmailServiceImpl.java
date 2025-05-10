package com.example.grand.email.service.impl;

import com.example.grand.common.ExceptionType;
import com.example.grand.common.exception.RestAPIException;
import com.example.grand.email.domain.EmailData;
import com.example.grand.email.dto.EmailDataDto;
import com.example.grand.email.dto.converter.EmailDataConverter;
import com.example.grand.email.repository.EmailDataRepository;
import com.example.grand.email.service.EmailService;
import com.example.grand.email.validator.EmailDataValidator;
import com.example.grand.security.jwt.JwtService;
import com.example.grand.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailDataValidator emailDataValidator;
    private final EmailDataConverter emailDataConverter;
    private final JwtService jwtService;
    private final EmailDataRepository emailDataRepository;

    @Override
    @Transactional
    public EmailDataDto create(EmailDataDto dto) {
        emailDataValidator.validateEmailData(dto);

        User user = jwtService.getUser();

        EmailData entity = emailDataConverter.toEntity(dto);
        entity.setUser(user);

        EmailData savedEntity = emailDataRepository.save(entity);

        return emailDataConverter.toDto(savedEntity);
    }

    @Override
    @Transactional
    public EmailDataDto update(EmailDataDto dto) {
        emailDataValidator.validateEmailData(dto);

        EmailData entity = findEmailDataByUuidAndUserUuid(dto.getUuid(), jwtService.getUser().getUuid());

        entity = emailDataConverter.toEntity(entity, dto);

        EmailData savedEntity = emailDataRepository.save(entity);

        return emailDataConverter.toDto(savedEntity);
    }

    @Override
    @Transactional
    public void delete(UUID emailUuid) {
        emailDataValidator.validateDeleteEmailData(jwtService.getUser().getUuid());

        EmailData entity = findEmailDataByUuidAndUserUuid(emailUuid, jwtService.getUser().getUuid());

        emailDataRepository.deleteById(entity.getId());
    }

    private EmailData findEmailDataByUuidAndUserUuid(UUID emailUuid, UUID userUuid) {
        return emailDataRepository.findByUuidAndUserUuid(emailUuid, userUuid)
                .orElseThrow(() -> new RestAPIException(ExceptionType.EMAIL_DATA_NOT_FOUND));
    }

}
