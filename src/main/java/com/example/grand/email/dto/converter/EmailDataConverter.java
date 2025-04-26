package com.example.grand.email.dto.converter;

import com.example.grand.email.domain.EmailData;
import com.example.grand.email.dto.EmailDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class EmailDataConverter {

    public EmailDataDto toDto(EmailData entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        EmailDataDto dto = new EmailDataDto();

        dto.setUuid(entity.getUuid());
        dto.setEmail(entity.getEmail());

        return dto;
    }

    public EmailData toEntity(EmailDataDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        EmailData entity = new EmailData();

        return fillEntity(entity, dto);
    }

    public EmailData toEntity(EmailData entity, EmailDataDto dto) {
        if (Objects.isNull(entity) || Objects.isNull(dto)) {
            return null;
        }
        return fillEntity(entity, dto);
    }

    private EmailData fillEntity(EmailData entity, EmailDataDto dto) {
        if (Objects.isNull(entity)) {
            return null;
        }
        if (Objects.isNull(dto)) {
            return entity;
        }

        entity.setEmail(dto.getEmail());

        return entity;
    }

}
