package com.example.grand.email.dto;

import com.example.grand.common.dto.IndexedDto;
import com.example.grand.email.validator.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDataDto extends IndexedDto {

    @Email
    private String email;

}
