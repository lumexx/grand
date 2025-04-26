package com.example.grand.account.validator;

import com.example.grand.account.domain.Account;
import com.example.grand.common.ExceptionType;
import com.example.grand.common.exception.RestAPIException;
import com.example.grand.utils.NumberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccountValidator {
    public void validateInput(UUID fromId, UUID toId, BigDecimal amount) {
        if (fromId.equals(toId)) {
            throw new RestAPIException(ExceptionType.SELF_TRANSFER_FORBIDDEN);
        }
        if (Objects.isNull(amount) || NumberUtils.lessThanOrEqual(amount, BigDecimal.ZERO)) {
            throw new RestAPIException(ExceptionType.NEGATIVE_BALANCE_FORBIDDEN);
        }
    }

    public void validateAccounts(Account from, BigDecimal amount) {
        if (NumberUtils.lessThan(from.getBalance(), amount)) {
            throw new RestAPIException(ExceptionType.NOT_ENOUGH_BALANCE);
        }
    }

}
