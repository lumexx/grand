package com.example.grand.account.repository;

import com.example.grand.account.domain.Account;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Account a WHERE a.uuid = :uuid")
    Optional<Account> findByUuidForUpdate(@Param("uuid") UUID accountUuid);

    Optional<Account> findByUserId(Long userId);

}