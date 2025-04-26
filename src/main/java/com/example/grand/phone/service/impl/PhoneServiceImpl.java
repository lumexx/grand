package com.example.grand.phone.service.impl;

import com.example.grand.common.ExceptionType;
import com.example.grand.phone.domain.PhoneData;
import com.example.grand.security.jwt.JwtService;
import com.example.grand.user.domain.User;
import com.example.grand.phone.dto.PhoneDataDto;
import com.example.grand.phone.dto.converter.PhoneDataConverter;
import com.example.grand.common.exception.RestAPIException;
import com.example.grand.phone.repository.PhoneDataRepository;
import com.example.grand.user.repository.UserRepository;
import com.example.grand.phone.service.PhoneService;
import com.example.grand.phone.validator.PhoneDataValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {

    private final PhoneDataValidator phoneDataValidator;
    private final PhoneDataConverter phoneDataConverter;
    private final UserRepository userRepository;
    private final PhoneDataRepository phoneDataRepository;
    private final JwtService jwtService;

    @Override
    @Transactional
    public PhoneDataDto create(PhoneDataDto dto) {
        phoneDataValidator.validatePhoneData(dto);

        User user = jwtService.getUser();

        PhoneData entity = phoneDataConverter.toEntity(dto);
        entity.setUser(user);

        PhoneData savedEntity = phoneDataRepository.save(entity);

        return phoneDataConverter.toDto(savedEntity);
    }

    @Override
    @Transactional
    public PhoneDataDto update(PhoneDataDto dto) {
        phoneDataValidator.validatePhoneData(dto);

        PhoneData entity = findPhoneDataByUuidAndUserUuid(dto.getUuid(), jwtService.getUser().getUuid());

        entity = phoneDataConverter.toEntity(entity, dto);

        PhoneData savedEntity = phoneDataRepository.save(entity);

        return phoneDataConverter.toDto(savedEntity);
    }

    @Override
    @Transactional
    public void delete(UUID phoneUuid) {
        PhoneData entity = findPhoneDataByUuidAndUserUuid(phoneUuid, jwtService.getUser().getUuid());

        phoneDataRepository.deleteById(entity.getId());
    }

    private User findAndValidateUser(UUID userUuid) {
        return userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new RestAPIException(ExceptionType.USER_NOT_FOUND));
    }

    private PhoneData findPhoneDataByUuidAndUserUuid(UUID phoneUuid, UUID userUuid) {
        return phoneDataRepository.findByUuidAndUserUuid(phoneUuid, userUuid)
                .orElseThrow(() -> new RestAPIException(ExceptionType.EMAIL_DATA_NOT_FOUND));
    }

}