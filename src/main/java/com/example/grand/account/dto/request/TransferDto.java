package com.example.grand.account.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class TransferDto {

    @Positive
    @NonNull
    private BigDecimal amount;

    @NonNull
    private UUID toAccountUuid;

}
