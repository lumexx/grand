package com.example.grand.security.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@FieldNameConstants(level = AccessLevel.PRIVATE)
@ConfigurationProperties("app")
public class JwtProperties {

	private Integer accessTokenTtlSeconds;
	private String jwtSecret;
	private Integer authSessionTtlMinutes;

}
