package com.example.grand.account.service.impl;

import com.example.grand.account.domain.Account;
import com.example.grand.account.dto.TransferLogDto;
import com.example.grand.account.dto.request.TransferDto;
import com.example.grand.account.repository.AccountRepository;
import com.example.grand.account.service.AccountService;
import com.example.grand.account.validator.AccountValidator;
import com.example.grand.common.ExceptionType;
import com.example.grand.common.exception.RestAPIException;
import com.example.grand.security.jwt.JwtService;
import com.example.grand.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final JwtService jwtService;
    private final AccountValidator accountValidator;

    @Override
    @Transactional
    public TransferLogDto transfer(TransferDto request) {
        User user = jwtService.getUser();

        UUID fromUuid = getSenderAccountUuid(user);
        UUID toUuid = request.getToAccountUuid();

        accountValidator.validateInput(fromUuid, toUuid, request.getAmount());

        Map<UUID, Account> locked = lockAccountsOrdered(fromUuid, toUuid);
        Account from = locked.get(fromUuid);
        Account to = locked.get(toUuid);

        accountValidator.validateAccounts(from, request.getAmount());

        from.setBalance(from.getBalance().subtract(request.getAmount()));
        to.setBalance(to.getBalance().add(request.getAmount()));

        accountRepository.save(from);
        accountRepository.save(to);

        TransferLogDto transferLog = TransferLogDto.builder()
                .fromAccountUuid(fromUuid)
                .toAccountUuid(toUuid)
                .amount(request.getAmount())
                .createdAt(LocalDateTime.now())
                .build();

        log.info("Transfer made: {}", transferLog);

        return transferLog;
    }

    private Map<UUID, Account> lockAccountsOrdered(UUID fromId, UUID toId) {
        UUID first = fromId.compareTo(toId) < 0 ? fromId : toId;
        UUID second = fromId.compareTo(toId) < 0 ? toId : fromId;

        Account firstAcc = accountRepository.findByUuidForUpdate(first)
                .orElseThrow(() -> new RestAPIException(ExceptionType.ACCOUNT_NOT_FOUND));
        Account secondAcc = accountRepository.findByUuidForUpdate(second)
                .orElseThrow(() -> new RestAPIException(ExceptionType.ACCOUNT_NOT_FOUND));

        Map<UUID, Account> result = new HashMap<>();
        result.put(firstAcc.getUuid(), firstAcc);
        result.put(secondAcc.getUuid(), secondAcc);
        return result;
    }

    private UUID getSenderAccountUuid(User user){
        Account account = accountRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RestAPIException(ExceptionType.ACCOUNT_NOT_FOUND));
        return account.getUuid();
    }

}
