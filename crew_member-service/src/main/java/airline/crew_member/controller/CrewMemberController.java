package airline.crew_member.controller;

import airline.crew_member.dto.CrewMemberDto;
import airline.crew_member.dto.FlightDto;
import airline.crew_member.mapper.CrewMemberMapper;
import airline.crew_member.mapper.FlightMapper;
import airline.crew_member.model.CrewMember;
import airline.crew_member.model.Flight;
import airline.crew_member.service.CrewMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/crew")
@RequiredArgsConstructor
public class CrewMemberController {
    private final CrewMemberService crewMemberService;

    @GetMapping
    public ResponseEntity<List<CrewMemberDto>> getAllCrewMembers() {
        List<CrewMember> crewMembers = crewMemberService.getAll();
        return new ResponseEntity<>(crewMembers.stream().map(CrewMemberMapper::toCrewMemberDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrewMemberDto> getCrewMemberById(@PathVariable Integer id) {
        CrewMember crewMember = crewMemberService.getCrewMemberById(id);
        return new ResponseEntity<>(CrewMemberMapper.toCrewMemberDto(crewMember), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<CrewMemberDto> saveCrewMember(@RequestBody CrewMemberDto crewMemberDto) {
        CrewMember savedCrewMember = crewMemberService.addCrewMember(CrewMemberMapper.toCrewMember(crewMemberDto));
        return new ResponseEntity<>(CrewMemberMapper.toCrewMemberDto(savedCrewMember), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CrewMemberDto> updateCrewMember(@PathVariable Integer id, @RequestBody CrewMemberDto crewMemberDto) {
        CrewMember updatedCrewMember = crewMemberService.editCrewMember(id, CrewMemberMapper.toCrewMember(crewMemberDto));
        return new ResponseEntity<>(CrewMemberMapper.toCrewMemberDto(updatedCrewMember), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCrewMember(@PathVariable Integer id) {
        crewMemberService.deleteCrewMember(id);
    }

    @PostMapping("/{crewMemberId}/flight/{flightId}/add")
    public ResponseEntity<FlightDto> addCrewMemberToFlight(@PathVariable Integer crewMemberId, @PathVariable Integer flightId) {
        Flight flight = crewMemberService.addCrewMemberToFlight(crewMemberId, flightId);
        return new ResponseEntity<>(FlightMapper.toFlightDto(flight), HttpStatus.OK);
    }

    @DeleteMapping("/{crewMemberId}/flight/{flightId}/remove")
    public ResponseEntity<FlightDto> removeCrewMemberFromFlight(@PathVariable Integer crewMemberId, @PathVariable Integer flightId) {
        Flight flight = crewMemberService.removeCrewMemberFromFlight(crewMemberId, flightId);
        return new ResponseEntity<>(FlightMapper.toFlightDto(flight), HttpStatus.OK);
    }
}
