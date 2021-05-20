package airline.crew_member.service;

import airline.crew_member.exception.CrewMemberIsAlreadyAssignedToFlightException;
import airline.crew_member.exception.CrewMemberNotFoundException;
import airline.crew_member.exception.FlightNotFoundException;
import airline.crew_member.model.CrewMember;
import airline.crew_member.model.Flight;
import airline.crew_member.repo.CrewMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CrewMemberService {
    private final CrewMemberRepository crewMemberRepo;
    private final RestTemplate restTemplate;

    public List<CrewMember> getAll() {
        return crewMemberRepo.findAll();
    }

    public CrewMember getCrewMemberById(Integer id) {
        return crewMemberRepo.findById(id).orElseThrow(() -> new CrewMemberNotFoundException(id));
    }

    public CrewMember addCrewMember(CrewMember crewMember) {
        return crewMemberRepo.save(crewMember);
    }

    public CrewMember editCrewMember(Integer id, CrewMember crewMember) {
        if (crewMemberRepo.findById(id).isEmpty()) return crewMemberRepo.save(crewMember);
        CrewMember crewMemberToEdit = getCrewMemberById(id);
        crewMemberToEdit.setSurname(crewMember.getSurname());
        crewMemberToEdit.setName(crewMember.getName());
        crewMemberToEdit.setGender(crewMember.getGender());
        crewMemberToEdit.setRole(crewMember.getRole());
        crewMemberToEdit.setAge(crewMember.getAge());
        return crewMemberRepo.save(crewMemberToEdit);
    }

    public void deleteCrewMember(Integer id) {
        CrewMember crewMember = getCrewMemberById(id);
        crewMember.getFlights().forEach(flight -> flight.getCrew().remove(crewMember));
        crewMemberRepo.delete(crewMember);
    }

    @Transactional
    public Flight addCrewMemberToFlight(Integer crewMemberId, Integer flightId) {
        Flight flight;
        try {
            flight = restTemplate.getForObject("http://FLIGHT-SERVICE/flights/" + flightId, Flight.class);
        } catch (HttpClientErrorException e) {
            throw new FlightNotFoundException(flightId);
        }
        CrewMember crewMember = getCrewMemberById(crewMemberId);
        if (flight.getCrew().contains(crewMember))
            throw new CrewMemberIsAlreadyAssignedToFlightException(crewMemberId, flightId);
        crewMember.getFlights().add(flight);
        flight.getCrew().add(crewMember);
        return flight;
    }

    @Transactional
    public Flight removeCrewMemberFromFlight(Integer crewMemberId, Integer flightId) {
        Flight flight;
        try {
            flight = restTemplate.getForObject("http://FLIGHT-SERVICE/flights/" + flightId, Flight.class);
        } catch (HttpClientErrorException e) {
            throw new FlightNotFoundException(flightId);
        }
        CrewMember crewMember = getCrewMemberById(crewMemberId);
        crewMember.getFlights().remove(flight);
        flight.getCrew().remove(crewMember);
        return flight;
    }
}
