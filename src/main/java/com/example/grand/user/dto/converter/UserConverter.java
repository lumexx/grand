package com.example.grand.user.dto.converter;

import com.example.grand.email.dto.converter.EmailDataConverter;
import com.example.grand.phone.dto.converter.PhoneDataConverter;
import com.example.grand.user.domain.User;
import com.example.grand.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final PhoneDataConverter phoneDataConverter;
    private final EmailDataConverter emailDataConverter;

    public UserDto toDto(User entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        UserDto dto = new UserDto();

        dto.setUuid(entity.getUuid());
        dto.setName(entity.getName());
        dto.setDateOfBirth(entity.getDateOfBirth());

        if (CollectionUtils.isNotEmpty(entity.getPhones())) {
            dto.setPhones(entity.getPhones()
                    .stream()
                    .map(phoneDataConverter::toDto)
                    .toList());
        }

        if (CollectionUtils.isNotEmpty(entity.getEmails())) {
            dto.setEmails(entity.getEmails()
                    .stream()
                    .map(emailDataConverter::toDto)
                    .toList());
        }

        return dto;
    }

    public User toEntity(UserDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        User entity = new User();

        return fillEntity(entity, dto);
    }

    public User toEntity(User entity, UserDto dto) {
        if (Objects.isNull(entity) || Objects.isNull(dto)) {
            return null;
        }
        return fillEntity(entity, dto);
    }

    private User fillEntity(User entity, UserDto dto) {
        if (Objects.isNull(entity)) {
            return null;
        }
        if (Objects.isNull(dto)) {
            return entity;
        }

        entity.setName(dto.getName());
        entity.setDateOfBirth(dto.getDateOfBirth());

        return entity;
    }

}
