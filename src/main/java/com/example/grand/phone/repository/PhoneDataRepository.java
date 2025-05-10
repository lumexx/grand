package com.example.grand.phone.repository;

import com.example.grand.phone.domain.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {
    boolean existsByPhone(String phone);

    Optional<PhoneData> findByUuidAndUserUuid(UUID phoneUuid, UUID userUuid);

    long countByUserUuid(UUID userUuid);

}
