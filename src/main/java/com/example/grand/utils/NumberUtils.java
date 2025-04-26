package com.example.grand.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NumberUtils {
    public static boolean lessThan(BigDecimal x, BigDecimal y) {
        return x.compareTo(y) < 0;
    }

    public static boolean lessThanOrEqual(BigDecimal x, BigDecimal y) {
        return x.compareTo(y) <= 0;
    }

    public static boolean greaterThan(BigDecimal x, BigDecimal y) {
        return x.compareTo(y) > 0;
    }

    public static boolean greaterThanOrEqual(BigDecimal x, BigDecimal y) {
        return x.compareTo(y) >= 0;
    }

}
