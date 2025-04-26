package com.example.grand.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties {

	private String url;
	private String title;
	private String version;
	private String authorizationName;
	private String authorizationDescription;

}
