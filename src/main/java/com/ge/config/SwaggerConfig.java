package com.ge.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Sets;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.flag}")
    private String swaggerFlag;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).protocols(Sets.newHashSet("http")).groupName("tb")
                .apiInfo(apiInfo()).enable(Boolean.valueOf(swaggerFlag)).select()
                .apis(RequestHandlerSelectors.basePackage("com.ge.controller"))
                .paths(regex("/.*")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("REST APIs for the TICKET BOOKING MS",
                "This WebUI describe the REST APIs that can be used to manipulate the 'TicketBookingMicroService' information", "v1.0",
                "Terms of service",
                new Contact("RAMANUJADASU", "", "VEMURI0324@GMAIL.COM"), "License of API",
                "API license URL");
    }
}
