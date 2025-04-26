package com.example.grand.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("redis")
public class RedisProperties {
    private String host;
    private String password;
    private int port;
    private int database;
    private boolean ssl;
}
