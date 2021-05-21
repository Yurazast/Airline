package airline.cloud_gateway.config;

import airline.cloud_gateway.service.SwaggerService;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Data
@Configuration
@Primary
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "documentation.swagger")
public class SwaggerServicesConfiguration {
    private List<SwaggerService> services;
}
