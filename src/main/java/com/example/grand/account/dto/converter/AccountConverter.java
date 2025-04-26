package com.example.grand.account.dto.converter;

import com.example.grand.account.domain.Account;
import com.example.grand.account.dto.AccountDto;
import com.example.grand.user.dto.converter.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AccountConverter {

    private final UserConverter userConverter;

    public AccountDto toDto(Account entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        AccountDto dto = new AccountDto();

        dto.setUuid(entity.getUuid());
        dto.setBalance(entity.getBalance());

        dto.setUser(userConverter.toDto(entity.getUser()));

        return dto;
    }

    public Account toEntity(AccountDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        Account entity = new Account();

        return fillEntity(entity, dto);
    }

    public Account toEntity(Account entity, AccountDto dto) {
        if (Objects.isNull(entity) || Objects.isNull(dto)) {
            return null;
        }
        return fillEntity(entity, dto);
    }

    private Account fillEntity(Account entity, AccountDto dto) {
        if (Objects.isNull(entity)) {
            return null;
        }
        if (Objects.isNull(dto)) {
            return entity;
        }

        entity.setBalance(dto.getBalance());

        return entity;
    }

}
