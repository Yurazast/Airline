package airline.flight.dao;

import airline.flight.model.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightDao {
    List<Flight> getAll();
    Optional<Flight> getById(int id);
    Flight save(Flight flight);
    Flight update(Flight flight);
    void delete(Flight flight);
}
