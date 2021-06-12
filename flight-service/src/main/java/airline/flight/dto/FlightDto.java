package airline.flight.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Builder
public class FlightDto implements Serializable {
    private Integer id;
    private String departurePlace;
    private String arrivalPlace;
    private String airplane;
    private Date departureTime;
    private Date arrivalTime;
    private Set<CrewMemberDto> crew;
}
