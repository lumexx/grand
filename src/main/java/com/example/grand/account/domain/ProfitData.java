package com.example.grand.account.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@RedisHash("profit_data")
public class ProfitData {

    @Id
    private Long accountId;
    private BigDecimal maxAllowedProfit;

}
