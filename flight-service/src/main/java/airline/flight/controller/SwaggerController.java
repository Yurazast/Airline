package airline.flight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class SwaggerController {
    private static final String REDIRECT_SWAGGER = "redirect:/swagger-ui/index.html";

    @GetMapping({"", "/swagger"})
    public String redirectSwagger() {
        return REDIRECT_SWAGGER;
    }
}
