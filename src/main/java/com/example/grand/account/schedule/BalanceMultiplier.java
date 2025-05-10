package com.example.grand.account.schedule;

import com.example.grand.account.domain.Account;
import com.example.grand.account.domain.ProfitData;
import com.example.grand.account.repository.AccountRepository;
import com.example.grand.account.repository.ProfitDataRedisRepository;
import com.example.grand.common.Constant;
import com.example.grand.utils.NumberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BalanceMultiplier {

    private final ProfitDataRedisRepository profitDataRedisRepository;
    private final AccountRepository accountRepository;
    private final CacheManager cacheManager;

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
        Optional<ProfitData> cached = profitDataRedisRepository.findByAccountId(account.getId());

        if (cached.isPresent()) {
            return cached.get();
        } else {
            ProfitData profitData = new ProfitData(account.getId(), calculateMaxProfit(account.getInitialBalance()));
            evictProfitDataCache(account.getId());
            profitDataRedisRepository.save(profitData);
            return profitData;
        }
    }

    private void evictProfitDataCache(Long accountId) {
        cacheManager.getCache(Constant.PROFIT_DATA_CACHE).evict(accountId);
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