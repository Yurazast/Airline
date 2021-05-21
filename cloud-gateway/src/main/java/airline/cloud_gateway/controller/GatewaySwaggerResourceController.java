package airline.cloud_gateway.controller;

import airline.cloud_gateway.config.SwaggerServicesConfiguration;
import airline.cloud_gateway.service.SwaggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
@EnableAutoConfiguration
@RequiredArgsConstructor
public class GatewaySwaggerResourceController implements SwaggerResourcesProvider {
    private final SwaggerServicesConfiguration swaggerServicesConfiguration;

    @Override
    public List<SwaggerResource> get() {
        return this.swaggerServicesConfiguration.getServices().stream().map(this::createSwaggerResource).collect(Collectors.toList());
    }

    private SwaggerResource createSwaggerResource(SwaggerService service) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(service.getName());
        swaggerResource.setUrl(service.getUrl());
        swaggerResource.setSwaggerVersion(service.getVersion());
        return swaggerResource;
    }
}
