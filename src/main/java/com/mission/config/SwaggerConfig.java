package com.mission.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30) // swagger 설정의 핵심이 되는 bean
                .select() // Swagger에서 제공하는 기본 응답 코드
                .apis(RequestHandlerSelectors.any()) // api 스펙이 작성되어 있는 패키지 (Controller) 를 지정
                .paths(PathSelectors.any()) // apis 에 있는 API 중 특정 path 를 선택
                .build();
    }
}