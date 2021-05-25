package airline.flight.service;

import airline.flight.dto.FlightDto;
import airline.flight.exception.FlightNotFoundException;
import airline.flight.mapper.FlightMapper;
import airline.flight.model.Flight;
import airline.flight.dao.FlightDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightDao flightDao;

    public List<FlightDto> getAllFlights() {
        return flightDao.getAll().stream().map(FlightMapper::toFlightDto).collect(Collectors.toList());
    }

    public FlightDto getFlightById(Integer id) {
        return FlightMapper.toFlightDto(flightDao.getById(id).orElseThrow(() -> new FlightNotFoundException(id)));
    }

    public FlightDto saveFlight(FlightDto flightDto) {
        Flight flightToSave = FlightMapper.toFlight(flightDto);
        return FlightMapper.toFlightDto(flightDao.save(flightToSave));
    }

    public FlightDto updateFlight(Integer id, FlightDto flightDto) {
        if (flightDao.getById(id).isEmpty()) {
            Flight flightToSave = FlightMapper.toFlight(flightDto);
            return FlightMapper.toFlightDto(flightDao.save(flightToSave));
        }
        Flight flightToUpdate = flightDao.getById(id).get();
        Flight updatedFlight = FlightMapper.update(flightToUpdate, flightDto);
        return FlightMapper.toFlightDto(flightDao.update(updatedFlight));
    }

    public void deleteFlight(Integer id) {
        Flight flightToDelete = flightDao.getById(id).orElseThrow(() -> new FlightNotFoundException(id));
        flightToDelete.getCrew().forEach(crewMember -> crewMember.getFlights().remove(flightToDelete));
        flightDao.delete(flightToDelete);
    }
}
