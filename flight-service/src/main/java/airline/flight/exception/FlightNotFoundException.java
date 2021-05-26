package airline.flight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends RuntimeException {
    public <T> FlightNotFoundException(final String attribute, final T value) {
        super(MessageFormat.format("Couldn't find flight with {0}: {1}", attribute, value));
    }
}
