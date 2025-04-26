package com.example.grand.account.dto;

import com.example.grand.common.dto.IndexedDto;
import com.example.grand.user.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountDto extends IndexedDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserDto user;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal balance;

}
