package airline.crew_member.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Builder
public class FlightDto {
    private Integer id;
    private String departurePlace;
    private String arrivalPlace;
    private String airplane;
    private Date departureTime;
    private Date arrivalTime;
    private Set<CrewMemberDto> crew;
}
