package airline.cloud_gateway.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "documentation.swagger.services")
public class SwaggerService {
    private String name;
    private String url;
    private String version;
}
