package com.example.grand.phone.service;

import com.example.grand.phone.dto.PhoneDataDto;

import java.util.UUID;

public interface PhoneService {
    PhoneDataDto create(PhoneDataDto dto);

    PhoneDataDto update(PhoneDataDto dto);

    void delete(UUID phoneUuid);

}
