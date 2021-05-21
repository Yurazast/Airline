package airline.cloud_gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class FallBackMethodController {
    @GetMapping("/crewMemberServiceFallBack")
    @ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
    public String crewMemberServiceFallBackMethod() {
        return "CrewMember Service is not available. Please try again later.";
    }

    @GetMapping("/flightServiceFallBack")
    @ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
    public String flightServiceFallBackMethod() {
        return "Flight Service is not available. Please try again later.";
    }
}
