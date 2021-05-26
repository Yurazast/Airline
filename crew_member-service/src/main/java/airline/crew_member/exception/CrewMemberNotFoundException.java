package airline.crew_member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CrewMemberNotFoundException extends RuntimeException {
    public <T> CrewMemberNotFoundException(final String attribute, final T value) {
        super(MessageFormat.format("Couldn't find crew member with {0}: {1}", attribute, value));
    }
}
