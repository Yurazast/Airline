package airline.flight.service;

import airline.flight.dto.FlightDto;
import airline.flight.exception.FlightNotFoundException;
import airline.flight.mapper.FlightMapper;
import airline.flight.model.Flight;
import airline.flight.repo.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

    public List<FlightDto> getAll() {
        return flightRepository.findAll().stream().map(FlightMapper::toFlightDto).collect(Collectors.toList());
    }

    public FlightDto getFlightById(Integer id) {
        return FlightMapper.toFlightDto(flightRepository.findById(id).orElseThrow(() -> new FlightNotFoundException(id)));
    }

    public FlightDto saveFlight(FlightDto flightDto) {
        Flight flightToSave = FlightMapper.toFlight(flightDto);
        return FlightMapper.toFlightDto(flightRepository.save(flightToSave));
    }

    public FlightDto updateFlight(Integer id, FlightDto flightDto) {
        if (flightRepository.findById(id).isEmpty()) {
            Flight flightToSave = FlightMapper.toFlight(flightDto);
            return FlightMapper.toFlightDto(flightRepository.save(flightToSave));
        }
        Flight flightToUpdate = flightRepository.findById(id).orElseThrow(() -> new FlightNotFoundException(id));
        Flight updatedFlight = FlightMapper.update(flightToUpdate, flightDto);
        return FlightMapper.toFlightDto(flightRepository.save(updatedFlight));
    }

    public void deleteFlight(Integer id) {
        Flight flightToDelete = flightRepository.findById(id).orElseThrow(() -> new FlightNotFoundException(id));
        flightToDelete.getCrew().forEach(crewMember -> crewMember.getFlights().remove(flightToDelete));
        flightRepository.delete(flightToDelete);
    }
}
