package com.example.grand.account.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class TransferLogDto {

    private UUID fromAccountUuid;
    private UUID toAccountUuid;
    private BigDecimal amount;
    private LocalDateTime createdAt;

}