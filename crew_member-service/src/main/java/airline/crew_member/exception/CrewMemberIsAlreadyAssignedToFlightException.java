package airline.crew_member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CrewMemberIsAlreadyAssignedToFlightException extends RuntimeException {
    public CrewMemberIsAlreadyAssignedToFlightException(final Integer crewMemberId, final Integer flightId) {
        super(MessageFormat.format("Crew member with id: {0} is already assigned to flight with id: {1}", crewMemberId, flightId));
    }
}
