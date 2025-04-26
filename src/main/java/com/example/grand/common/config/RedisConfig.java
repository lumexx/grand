package com.example.grand.common.config;

import com.example.grand.common.properties.RedisProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private static final String ADDRESS_FORMAT = "%s://%s:%s";

    private final RedisProperties redisProperties;

    @Bean
    RedissonClient redissonClient() {
        return Redisson.create(config());
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10))  // Время жизни кэша, можно настроить
                .disableCachingNullValues();  // Отключаем кэширование null значений

        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
                .cacheDefaults(cacheConfig)
                .build();
    }

    private Config config() {
        final Config config = new Config();

        final SingleServerConfig singleServerConfig = config.useSingleServer();

        singleServerConfig
                .setAddress(getRedisAddress())
                .setDatabase(redisProperties.getDatabase());

        if (StringUtils.isNotEmpty(redisProperties.getPassword())) {
            singleServerConfig.setPassword(redisProperties.getPassword());
        }

        return config;
    }

    private String getRedisAddress() {
        return String.format(
                ADDRESS_FORMAT,
                redisProperties.isSsl() ? "rediss" : "redis",
                redisProperties.getHost(),
                redisProperties.getPort()
        );
    }

}