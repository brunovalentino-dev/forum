package io.bvalentino.forum.config.docs

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
class SwaggerConfig {

    @Bean
    fun forumOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("Forum API")
                .description("Forum API sample application")
                .version("v1.0.0")
                .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("Forum API Documentation")
                    .url("http://localhost:8080/swagger-ui/index.html")
            )
    }

}