package dio.web.api.doc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Title - Rest API")
                .description("API exemplo de uso de Springboot REST API")
                .version("1.0")
                .termsOfService("Termo de uso: Open Source")
                .license(new License()
                    .name("Licença - Sua Empresa")
                    .url("http://www.seusite.com.br"))
                .contact(new Contact()
                    .name("DIO")
                    .url("https://dio.me")
                    .email("N5YR2@example.com")
                )
            )
            .externalDocs(new ExternalDocumentation()
                .description("Documentação externa")
                .url("https://dio.me"));
    }
}