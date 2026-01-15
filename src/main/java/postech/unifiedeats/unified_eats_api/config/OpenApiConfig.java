package postech.unifiedeats.unified_eats_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI unifiedEatsAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("Unified Eats API")
                    .description("Backend para sistema unificado de gestão de restaurantes")
                    .version("v1")
                ).addServersItem(new Server()
                    .url("http://localhost:8080")
                    .description("Servidor local"));
    }
}
