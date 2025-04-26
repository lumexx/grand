package com.example.grand.common.config;

import com.example.grand.common.Constant;
import com.example.grand.common.properties.SwaggerProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    private static final String JWT = "JWT";

    private final SwaggerProperties properties;

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl(properties.getUrl());
        return new OpenAPI()
                .info(new Info().title(properties.getTitle()).version(properties.getVersion()))
                .servers(List.of(server))
                .components(new Components().addSecuritySchemes(Constant.BEARER, apiKeySecuritySchema()))
                .security(Collections.singletonList(new SecurityRequirement().addList(Constant.BEARER)));
    }

    public SecurityScheme apiKeySecuritySchema() {
        return new SecurityScheme()
                .name(properties.getAuthorizationName())
                .description(properties.getAuthorizationDescription())
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.HTTP)
                .scheme(Constant.BEARER)
                .bearerFormat(JWT);
    }

}
