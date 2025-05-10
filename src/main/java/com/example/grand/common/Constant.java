package com.example.grand.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constant {

    public static final String UUID = "uuid";

    public static final String BEARER = "Bearer";

    public static final String USERNAME_SPLIT_SYMBOL = ",";

    public static final String BEARER_TOKEN_PREFIX = BEARER + StringUtils.SPACE;

    public static final BigDecimal HUNDRED_PERCENT = BigDecimal.valueOf(100);

    public static final BigDecimal INCREASE_BALANCE_PERCENT = BigDecimal.valueOf(10);

    public static final BigDecimal MAX_BALANCE_INCREASE_PERCENT = BigDecimal.valueOf(207);

    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public static final int DECIMAL_COUNT = 10;

    public static final String PROFIT_DATA_CACHE = "profitDataCache";

}
