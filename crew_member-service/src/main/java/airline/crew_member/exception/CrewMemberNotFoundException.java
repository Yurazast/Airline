package airline.crew_member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CrewMemberNotFoundException extends RuntimeException {
    public CrewMemberNotFoundException(final Integer id) {
        super(MessageFormat.format("Couldn't find crew member with id: {0}", id));
    }
}
