package com.sistema.factura.Seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwagerConfig {
    private static final String SecuritySchemeName = "bearerAuth";
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                    .title("API PRUEBA")
                    .version("1.0.1")
                    .description("Documentacion de api de prueba")
                    .contact(new Contact()
                        .name("Luis Pinos")
                        .email("luisfpinos@gmail.com")
                    )
                    .license(new License()
                        .name("MIT")
                        .url("https://choosealicense.com/licenses/mit/")
                    )
                )
                .addSecurityItem(new SecurityRequirement().addList(SecuritySchemeName))
                .components(new Components()
                    .addSecuritySchemes(SecuritySchemeName, new SecurityScheme()
                        .name(SecuritySchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                    )
                );
    }
}
