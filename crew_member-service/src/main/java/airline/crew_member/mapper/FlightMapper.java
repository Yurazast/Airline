package airline.crew_member.mapper;

import airline.crew_member.dto.FlightDto;
import airline.crew_member.model.Flight;

import java.util.HashSet;
import java.util.stream.Collectors;

public class FlightMapper {
    public static FlightDto toFlightDto(Flight flight) {
        return FlightDto.builder()
                .id(flight.getId())
                .departurePlace(flight.getDeparturePlace())
                .arrivalPlace(flight.getArrivalPlace())
                .airplane(flight.getAirplane())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .crew(flight.getCrew().stream().map(CrewMemberMapper::toCrewMemberDto).collect(Collectors.toSet()))
                .build();
    }
}
