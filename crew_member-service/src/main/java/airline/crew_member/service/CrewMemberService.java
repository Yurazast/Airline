package airline.crew_member.service;

import airline.crew_member.dto.CrewMemberDto;
import airline.crew_member.dto.FlightDto;
import airline.crew_member.exception.CrewMemberIsAlreadyAssignedToFlightException;
import airline.crew_member.exception.CrewMemberNotFoundException;
import airline.crew_member.exception.FlightNotFoundException;
import airline.crew_member.mapper.CrewMemberMapper;
import airline.crew_member.mapper.FlightMapper;
import airline.crew_member.model.CrewMember;
import airline.crew_member.model.Flight;
import airline.crew_member.dao.CrewMemberDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CrewMemberService {
    private final CrewMemberDao crewMemberDao;
    private final RestTemplate restTemplate;

    public List<CrewMemberDto> getAllCrewMembers() {
        return crewMemberDao.getAll().stream().map(CrewMemberMapper::toCrewMemberDto).collect(Collectors.toList());
    }

    public CrewMemberDto getCrewMemberById(Integer id) {
        return CrewMemberMapper.toCrewMemberDto(crewMemberDao.getById(id)
                .orElseThrow(() -> new CrewMemberNotFoundException("id", id)));
    }

    public CrewMemberDto getCrewMemberBySurname(String surname) {
        return CrewMemberMapper.toCrewMemberDto(crewMemberDao.getBySurname(surname)
                .orElseThrow(() -> new CrewMemberNotFoundException("surname", surname)));
    }

    public CrewMemberDto getCrewMemberByName(String name) {
        return CrewMemberMapper.toCrewMemberDto(crewMemberDao.getByName(name)
                .orElseThrow(() -> new CrewMemberNotFoundException("name", name)));
    }

    public CrewMemberDto saveCrewMember(CrewMemberDto crewMemberDto) {
        CrewMember crewMemberToSave = CrewMemberMapper.toCrewMember(crewMemberDto);
        return CrewMemberMapper.toCrewMemberDto(crewMemberDao.save(crewMemberToSave));
    }

    public CrewMemberDto updateCrewMember(Integer id, CrewMemberDto crewMemberDto) {
        if (crewMemberDao.getById(id).isEmpty()) {
            CrewMember crewMemberToSave = CrewMemberMapper.toCrewMember(crewMemberDto);
            return CrewMemberMapper.toCrewMemberDto(crewMemberDao.save(crewMemberToSave));
        }
        CrewMember crewMemberToUpdate = crewMemberDao.getById(id).get();
        CrewMember updatedCrewMember = CrewMemberMapper.update(crewMemberToUpdate, crewMemberDto);
        return CrewMemberMapper.toCrewMemberDto(crewMemberDao.update(updatedCrewMember));
    }

    public void deleteCrewMember(Integer id) {
        CrewMember crewMemberToDelete = crewMemberDao.getById(id)
                .orElseThrow(() -> new CrewMemberNotFoundException("id", id));
        crewMemberToDelete.getFlights().forEach(flight -> flight.getCrew().remove(crewMemberToDelete));
        crewMemberDao.delete(crewMemberToDelete);
    }

    @Transactional
    public FlightDto addCrewMemberToFlight(Integer crewMemberId, Integer flightId) {
        Flight flight;
        try {
            flight = restTemplate.getForObject("http://FLIGHT-SERVICE/flights/" + flightId, Flight.class);
        } catch (HttpClientErrorException e) {
            throw new FlightNotFoundException("id", flightId);
        }
        CrewMember crewMember = crewMemberDao.getById(crewMemberId)
                .orElseThrow(() -> new CrewMemberNotFoundException("id", crewMemberId));
        if (flight.getCrew().contains(crewMember))
            throw new CrewMemberIsAlreadyAssignedToFlightException(crewMemberId, flightId);
        crewMember.getFlights().add(flight);
        flight.getCrew().add(crewMember);
        return FlightMapper.toFlightDto(flight);
    }

    @Transactional
    public FlightDto removeCrewMemberFromFlight(Integer crewMemberId, Integer flightId) {
        Flight flight;
        try {
            flight = restTemplate.getForObject("http://FLIGHT-SERVICE/flights/" + flightId, Flight.class);
        } catch (HttpClientErrorException e) {
            throw new FlightNotFoundException("id", flightId);
        }
        CrewMember crewMember = crewMemberDao.getById(crewMemberId)
                .orElseThrow(() -> new CrewMemberNotFoundException("id", crewMemberId));
        crewMember.getFlights().remove(flight);
        flight.getCrew().remove(crewMember);
        return FlightMapper.toFlightDto(flight);
    }
}
