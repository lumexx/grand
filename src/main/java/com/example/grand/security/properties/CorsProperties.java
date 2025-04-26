package com.example.grand.security.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("cors")
@Getter
@Setter
public class CorsProperties {

	private String[] allowedOrigins;

}
