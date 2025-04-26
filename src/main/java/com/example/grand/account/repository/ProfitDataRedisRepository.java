package com.example.grand.account.repository;

import com.example.grand.account.domain.ProfitData;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfitDataRedisRepository extends CrudRepository<ProfitData, String> {

    @Cacheable(value = "profitDataCache", key = "#accountId")
    Optional<ProfitData> findByAccountId(Long accountId);

}