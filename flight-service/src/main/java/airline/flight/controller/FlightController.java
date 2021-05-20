package airline.flight.controller;

import airline.flight.dto.FlightDto;
import airline.flight.mapper.FlightMapper;
import airline.flight.model.Flight;
import airline.flight.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        List<Flight> flights = flightService.getAll();
        return new ResponseEntity<>(flights.stream().map(FlightMapper::toFlightDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Integer id) {
        Flight flight = flightService.getFlightById(id);
        return new ResponseEntity<>(FlightMapper.toFlightDto(flight), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<FlightDto> saveFlight(@RequestBody FlightDto flightDto) {
        Flight savedFlight = flightService.addFlight(FlightMapper.toFlight(flightDto));
        return new ResponseEntity<>(FlightMapper.toFlightDto(savedFlight), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable Integer id, @RequestBody FlightDto flightDto) {
        Flight updatedFlight = flightService.editFlight(id, FlightMapper.toFlight(flightDto));
        return new ResponseEntity<>(FlightMapper.toFlightDto(updatedFlight), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Integer id) {
        flightService.deleteFlight(id);
    }
}
