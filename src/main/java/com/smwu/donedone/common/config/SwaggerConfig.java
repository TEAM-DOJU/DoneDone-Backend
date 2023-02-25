package com.smwu.donedone.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "던던 API",
                description = "던던 API 문서입니다"
        ),
        servers = {
                @Server(url = "/", description = "local server"),
                @Server(url = "https://donedone-376207.uc.r.appspot.com", description = "deploy server")
        })
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/api/**"};

        return GroupedOpenApi.builder()
                .group("던던 API")
                .pathsToMatch(paths)
                .build();
    }
}
