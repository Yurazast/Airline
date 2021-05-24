package airline.flight.mapper;

import airline.flight.dto.FlightDto;
import airline.flight.model.Flight;

import java.util.HashSet;
import java.util.stream.Collectors;

public class FlightMapper {
    public static Flight toFlight(FlightDto flightDto) {
        return Flight.builder()
                .departurePlace(flightDto.getDeparturePlace())
                .arrivalPlace(flightDto.getArrivalPlace())
                .airplane(flightDto.getAirplane())
                .departureTime(flightDto.getDepartureTime())
                .arrivalTime(flightDto.getArrivalTime())
                .crew(new HashSet<>())
                .build();
    }

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

    public static Flight update(Flight oldFlight, FlightDto newFlight) {
        oldFlight.setDeparturePlace(newFlight.getDeparturePlace());
        oldFlight.setArrivalPlace(newFlight.getArrivalPlace());
        oldFlight.setAirplane(newFlight.getAirplane());
        oldFlight.setDepartureTime(newFlight.getDepartureTime());
        oldFlight.setArrivalTime(newFlight.getArrivalTime());
        return oldFlight;
    }
}
