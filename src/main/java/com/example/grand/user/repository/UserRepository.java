package com.example.grand.user.repository;

import com.example.grand.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByUuid(UUID uuid);

    @Query("SELECT u FROM User u JOIN u.emails e WHERE e.email IN :emails")
    Optional<User> findByEmailIn(@Param("emails") List<String> emails);

}
