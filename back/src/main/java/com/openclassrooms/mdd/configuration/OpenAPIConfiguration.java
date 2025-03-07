package com.openclassrooms.mdd.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact().name("Axel").email("axelker@outlook.fr");
        Info information = new Info()
                .title("Monde de DEV System API")
                .version("1.0")
                .description("This API exposes endpoints to manage article on dev.")
                .contact(contact);

        return new OpenAPI()
                .info(information)
                .addSecurityItem(new SecurityRequirement().addList("JWT-Cookie"))
                .components(new Components()
                        .addSecuritySchemes("JWT-Cookie", new SecurityScheme()
                                .name("jwt")
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.COOKIE)));

    }
}