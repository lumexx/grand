package com.example.grand.phone.validator;

import com.example.grand.common.ExceptionType;
import com.example.grand.phone.dto.PhoneDataDto;
import com.example.grand.common.exception.RestAPIException;
import com.example.grand.phone.repository.PhoneDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhoneDataValidator {
    private final PhoneDataRepository phoneDataRepository;

    public void validatePhoneData(PhoneDataDto dto) {
        boolean existsByPhone = phoneDataRepository.existsByPhone(dto.getPhone());

        if (existsByPhone) {
            throw new RestAPIException(ExceptionType.PHONE_ALREADY_EXISTS);
        }
    }

}
