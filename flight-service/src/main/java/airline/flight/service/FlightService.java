package airline.flight.service;

import airline.flight.exception.FlightNotFoundException;
import airline.flight.model.Flight;
import airline.flight.repo.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Integer id) {
        return flightRepository.findById(id).orElseThrow(() -> new FlightNotFoundException(id));
    }

    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight editFlight(Integer id, Flight flight) {
        if (flightRepository.findById(id).isEmpty()) return flightRepository.save(flight);
        Flight flightToEdit = getFlightById(id);
        flightToEdit.setDeparturePlace(flight.getDeparturePlace());
        flightToEdit.setArrivalPlace(flight.getArrivalPlace());
        flightToEdit.setAirplane(flight.getAirplane());
        flightToEdit.setDepartureTime(flight.getDepartureTime());
        flightToEdit.setArrivalTime(flight.getArrivalTime());
        return flightRepository.save(flightToEdit);
    }

    public void deleteFlight(Integer id) {
        Flight flight = getFlightById(id);
        flight.getCrew().forEach(crewMember -> crewMember.getFlights().remove(flight));
        flightRepository.delete(flight);
    }
}
