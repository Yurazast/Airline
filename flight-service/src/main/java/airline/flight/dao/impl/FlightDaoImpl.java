package airline.flight.dao.impl;

import airline.flight.dao.FlightDao;
import airline.flight.model.Flight;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class FlightDaoImpl implements FlightDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Flight> getAll() {
        return entityManager.createQuery("SELECT f FROM Flight f", Flight.class).getResultList();
    }

    @Override
    public Optional<Flight> getById(int id) {
        return Optional.ofNullable(entityManager.find(Flight.class, id));
    }

    @Override
    public Optional<Flight> getByDeparturePlace(String departurePlace) {
        return entityManager
                .createQuery("SELECT f FROM Flight f WHERE f.departurePlace = :departurePlace", Flight.class)
                .setParameter("departurePlace", departurePlace)
                .getResultList().stream().findFirst();
    }

    @Override
    public Optional<Flight> getByArrivalPlace(String arrivalPlace) {
        return entityManager
                .createQuery("SELECT f FROM Flight f WHERE f.arrivalPlace = :arrivalPlace", Flight.class)
                .setParameter("arrivalPlace", arrivalPlace)
                .getResultList().stream().findFirst();
    }

    @Transactional
    @Override
    public Flight save(Flight flight) {
        entityManager.persist(flight);
        return flight;
    }

    @Transactional
    @Override
    public Flight update(Flight flight) {
        entityManager.merge(flight);
        return flight;
    }

    @Transactional
    @Override
    public void delete(Flight flight) {
        entityManager.remove(flight);
    }
}
