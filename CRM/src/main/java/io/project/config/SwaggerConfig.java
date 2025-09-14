package io.project.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicClientApi(){
        return GroupedOpenApi.builder()
                .group("Clients")
                .pathsToMatch("/clients/**")
                .build();
    }
    @Bean
    public  GroupedOpenApi publicProductApi(){
        return GroupedOpenApi.builder()
                .group("Products")
                .pathsToMatch("/products/**")
                .build();
    }
    @Bean
    public  GroupedOpenApi publicEmployeeApi(){
        return GroupedOpenApi.builder()
                .group("Employees")
                .pathsToMatch("/employees/**")
                .build();
    }
    @Bean
    public  GroupedOpenApi publicOrderApi(){
        return GroupedOpenApi.builder()
                .group("Orders")
                .pathsToMatch("/orders/**")
                .build();
    }
}
