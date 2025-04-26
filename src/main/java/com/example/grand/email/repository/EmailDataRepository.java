package com.example.grand.email.repository;

import com.example.grand.email.domain.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmailDataRepository extends JpaRepository<EmailData, Long> {
    boolean existsByEmail(String email);

    Optional<EmailData> findByUuidAndUserUuid(UUID emailUuid, UUID userUuid);

}
