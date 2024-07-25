package br.com.alura.challenge.adopet.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Adopet")
                        .description("API Rest da aplicação Adopet, desafio de backend da alura")
                        .contact(new Contact()
                                .name("Pedro Henrique dos Santos")
                                .email("ph6704938@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://Adopet/api/licenca")));
    }


}
