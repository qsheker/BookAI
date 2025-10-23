package org.qsheker.aiservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("AI Service API")
                        .description("REST API documentation for the AI microservice (Ollama-based)")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Aldiyar Qsheker")
                                .email("aldikzhaks@gmail.com")
                                .url("https://github.com/qsheker"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                );
    }
}
