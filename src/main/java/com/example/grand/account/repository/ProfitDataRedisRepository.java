package com.example.grand.account.repository;

import com.example.grand.account.domain.ProfitData;
import com.example.grand.common.Constant;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfitDataRedisRepository extends CrudRepository<ProfitData, String> {

    @Cacheable(value = Constant.PROFIT_DATA_CACHE, key = "#accountId")
    Optional<ProfitData> findByAccountId(Long accountId);

}