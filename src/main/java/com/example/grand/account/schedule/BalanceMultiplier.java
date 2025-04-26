package com.example.grand.account.schedule;

import com.example.grand.account.domain.Account;
import com.example.grand.account.domain.ProfitData;
import com.example.grand.account.repository.AccountRepository;
import com.example.grand.account.repository.ProfitDataRedisRepository;
import com.example.grand.common.Constant;
import com.example.grand.utils.NumberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BalanceMultiplier {

    private final ProfitDataRedisRepository profitDataRedisRepository;
    private final AccountRepository accountRepository;

    @Scheduled(cron = "0/30 * * * * *")
    private void updateBalance() {
        List<Account> accounts = accountRepository.findAll();

        for (Account account : accounts) {
            ProfitData profitData = getOrCreateProfitData(account);

            if (NumberUtils.lessThan(account.getBalance(), profitData.getMaxAllowedProfit())) {
                BigDecimal gain = calculateGain(account.getBalance());

                if (NumberUtils.greaterThan(account.getBalance().add(gain), profitData.getMaxAllowedProfit())) {
                    gain = profitData.getMaxAllowedProfit().subtract(account.getBalance());
                }

                account.setBalance(account.getBalance().add(gain));
                accountRepository.save(account);
            }
        }
    }

    private ProfitData getOrCreateProfitData(Account account) {
        return profitDataRedisRepository.findByAccountId(account.getId())
                .orElseGet(() -> {
                    ProfitData profitData = new ProfitData(account.getId(), calculateMaxProfit(account.getBalance()));
                    profitDataRedisRepository.save(profitData);
                    return profitData;
                });
    }

    private BigDecimal calculateGain(BigDecimal currentBalance) {
        return currentBalance.multiply(Constant.INCREASE_BALANCE_PERCENT)
                .divide(Constant.HUNDRED_PERCENT, Constant.DECIMAL_COUNT, Constant.ROUNDING_MODE);
    }

    private BigDecimal calculateMaxProfit(BigDecimal initialBalance) {
        return initialBalance.multiply(Constant.MAX_BALANCE_INCREASE_PERCENT)
                .divide(Constant.HUNDRED_PERCENT, Constant.DECIMAL_COUNT, Constant.ROUNDING_MODE);
    }

}