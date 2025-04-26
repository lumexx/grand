package com.example.grand.phone.dto.converter;

import com.example.grand.phone.domain.PhoneData;
import com.example.grand.phone.dto.PhoneDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PhoneDataConverter {

    public PhoneDataDto toDto(PhoneData entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        PhoneDataDto dto = new  PhoneDataDto();

        dto.setUuid(entity.getUuid());
        dto.setPhone(entity.getPhone());

        return dto;
    }

    public PhoneData toEntity(PhoneDataDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        PhoneData entity = new PhoneData();

        return fillEntity(entity, dto);
    }

    public PhoneData toEntity(PhoneData entity, PhoneDataDto dto) {
        if (Objects.isNull(entity) || Objects.isNull(dto)) {
            return null;
        }
        return fillEntity(entity, dto);
    }

    private PhoneData fillEntity(PhoneData entity, PhoneDataDto dto) {
        if (Objects.isNull(entity)) {
            return null;
        }
        if (Objects.isNull(dto)) {
            return entity;
        }

        entity.setPhone(dto.getPhone());

        return entity;
    }

}
