package airline.flight.controller;

import airline.flight.dto.FlightDto;
import airline.flight.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        List<FlightDto> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Integer id) {
        FlightDto flightById = flightService.getFlightById(id);
        return new ResponseEntity<>(flightById, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<FlightDto> saveFlight(@RequestBody FlightDto flightDto) {
        FlightDto savedFlight = flightService.saveFlight(flightDto);
        return new ResponseEntity<>(savedFlight, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable Integer id, @RequestBody FlightDto flightDto) {
        FlightDto updatedFlight = flightService.updateFlight(id, flightDto);
        return new ResponseEntity<>(updatedFlight, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Integer id) {
        flightService.deleteFlight(id);
    }
}
