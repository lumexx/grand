package com.example.grand;

import com.example.grand.common.exception.RestAPIException;
import com.example.grand.account.domain.Account;
import com.example.grand.account.dto.TransferLogDto;
import com.example.grand.account.dto.request.TransferDto;
import com.example.grand.account.repository.AccountRepository;
import com.example.grand.security.jwt.JwtService;
import com.example.grand.account.service.impl.AccountServiceImpl;
import com.example.grand.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@SpringBootTest
public class GrandTest {

    private final static UUID TEST_UUID_1 = UUID.fromString("84f6c1ab-74c2-4849-9a5d-a46e9521cc8d");
    private final static UUID TEST_UUID_2 = UUID.fromString("e54cb9a2-6276-4858-8b42-569699f54be3");

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private JwtService jwtService;

    private User user;
    private Account fromAccount;
    private Account toAccount;
    private TransferDto transferDto;

    @BeforeEach
    void setUp() {
        fromAccount = new Account();
        fromAccount.setUuid(TEST_UUID_1);
        fromAccount.setBalance(BigDecimal.valueOf(1000));

        toAccount = new Account();
        toAccount.setUuid(TEST_UUID_2);
        toAccount.setBalance(BigDecimal.valueOf(500));

        transferDto = new TransferDto();
        transferDto.setToAccountUuid(TEST_UUID_2);
        transferDto.setAmount(BigDecimal.valueOf(200));

        user = new User();
        user.setUuid(TEST_UUID_1);
    }

    @Test
    void transfer_shouldTransferMoneySuccessfully() {
        given(jwtService.getUser()).willReturn(user);
        given(accountRepository.findByUuidForUpdate(TEST_UUID_1)).willReturn(Optional.of(fromAccount));
        given(accountRepository.findByUuidForUpdate(TEST_UUID_2)).willReturn(Optional.of(toAccount));

        // when
        TransferLogDto result = accountService.transfer(transferDto);

        // then
        assertThat(result.getFromAccountUuid()).isEqualTo(TEST_UUID_1);
        assertThat(result.getToAccountUuid()).isEqualTo(TEST_UUID_2);
        assertThat(result.getAmount()).isEqualByComparingTo(BigDecimal.valueOf(200));
        assertThat(result.getCreatedAt()).isBeforeOrEqualTo(LocalDateTime.now());

        assertThat(fromAccount.getBalance()).isEqualByComparingTo(BigDecimal.valueOf(800));
        assertThat(toAccount.getBalance()).isEqualByComparingTo(BigDecimal.valueOf(700));

        then(accountRepository).should(times(1)).save(fromAccount);
        then(accountRepository).should(times(1)).save(toAccount);
    }

    @Test
    void transfer_shouldThrowException_whenAccountNotFound() {
        given(jwtService.getUser()).willReturn(user);
        given(accountRepository.findByUuidForUpdate(TEST_UUID_1)).willReturn(Optional.empty());

        // when & then
        assertThrows(RestAPIException.class, () -> accountService.transfer(transferDto));
    }
}