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
import airline.crew_member.repo.CrewMemberRepository;
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
    private final CrewMemberRepository crewMemberRepository;
    private final RestTemplate restTemplate;

    public List<CrewMemberDto> getAll() {
        return crewMemberRepository.findAll().stream().map(CrewMemberMapper::toCrewMemberDto).collect(Collectors.toList());
    }

    public CrewMemberDto getCrewMemberById(Integer id) {
        return CrewMemberMapper.toCrewMemberDto(crewMemberRepository.findById(id).orElseThrow(() -> new CrewMemberNotFoundException(id)));
    }

    public CrewMemberDto saveCrewMember(CrewMemberDto crewMemberDto) {
        CrewMember crewMemberToSave = CrewMemberMapper.toCrewMember(crewMemberDto);
        return CrewMemberMapper.toCrewMemberDto(crewMemberRepository.save(crewMemberToSave));
    }

    public CrewMemberDto updateCrewMember(Integer id, CrewMemberDto crewMemberDto) {
        if (crewMemberRepository.findById(id).isEmpty()) {
            CrewMember crewMemberToSave = CrewMemberMapper.toCrewMember(crewMemberDto);
            return CrewMemberMapper.toCrewMemberDto(crewMemberRepository.save(crewMemberToSave));
        }
        CrewMember crewMemberToUpdate = crewMemberRepository.findById(id).orElseThrow(() -> new CrewMemberNotFoundException(id));
        CrewMember updatedCrewMember = CrewMemberMapper.update(crewMemberToUpdate, crewMemberDto);
        return CrewMemberMapper.toCrewMemberDto(crewMemberRepository.save(updatedCrewMember));
    }

    public void deleteCrewMember(Integer id) {
        CrewMember crewMemberToDelete = crewMemberRepository.findById(id).orElseThrow(() -> new CrewMemberNotFoundException(id));
        crewMemberToDelete.getFlights().forEach(flight -> flight.getCrew().remove(crewMemberToDelete));
        crewMemberRepository.delete(crewMemberToDelete);
    }

    @Transactional
    public FlightDto addCrewMemberToFlight(Integer crewMemberId, Integer flightId) {
        Flight flight;
        try {
            flight = restTemplate.getForObject("http://FLIGHT-SERVICE/flights/" + flightId, Flight.class);
        } catch (HttpClientErrorException e) {
            throw new FlightNotFoundException(flightId);
        }
        CrewMember crewMember = crewMemberRepository.findById(crewMemberId).orElseThrow(() -> new CrewMemberNotFoundException(crewMemberId));
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
            throw new FlightNotFoundException(flightId);
        }
        CrewMember crewMember = crewMemberRepository.findById(crewMemberId).orElseThrow(() -> new CrewMemberNotFoundException(crewMemberId));
        crewMember.getFlights().remove(flight);
        flight.getCrew().remove(crewMember);
        return FlightMapper.toFlightDto(flight);
    }
}
