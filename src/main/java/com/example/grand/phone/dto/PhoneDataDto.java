package com.example.grand.phone.dto;

import com.example.grand.common.dto.IndexedDto;
import com.example.grand.phone.validator.Phone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDataDto extends IndexedDto {

    @Phone
    private String phone;

}
