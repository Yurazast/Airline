package airline.crew_member.controller;

import airline.crew_member.dto.CrewMemberDto;
import airline.crew_member.dto.FlightDto;
import airline.crew_member.service.CrewMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crew")
@RequiredArgsConstructor
public class CrewMemberController {
    private final CrewMemberService crewMemberService;

    @GetMapping
    public ResponseEntity<List<CrewMemberDto>> getAllCrewMembers() {
        List<CrewMemberDto> crewMembers = crewMemberService.getAll();
        return new ResponseEntity<>(crewMembers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrewMemberDto> getCrewMemberById(@PathVariable Integer id) {
        CrewMemberDto crewMemberById = crewMemberService.getCrewMemberById(id);
        return new ResponseEntity<>(crewMemberById, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<CrewMemberDto> saveCrewMember(@RequestBody CrewMemberDto crewMemberDto) {
        CrewMemberDto savedCrewMember = crewMemberService.saveCrewMember(crewMemberDto);
        return new ResponseEntity<>(savedCrewMember, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CrewMemberDto> updateCrewMember(@PathVariable Integer id, @RequestBody CrewMemberDto crewMemberDto) {
        CrewMemberDto updatedCrewMember = crewMemberService.updateCrewMember(id, crewMemberDto);
        return new ResponseEntity<>(updatedCrewMember, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCrewMember(@PathVariable Integer id) {
        crewMemberService.deleteCrewMember(id);
    }

    @PostMapping("/{crewMemberId}/flights/{flightId}/add")
    public ResponseEntity<FlightDto> addCrewMemberToFlight(@PathVariable Integer crewMemberId, @PathVariable Integer flightId) {
        FlightDto alteredFlight = crewMemberService.addCrewMemberToFlight(crewMemberId, flightId);
        return new ResponseEntity<>(alteredFlight, HttpStatus.OK);
    }

    @DeleteMapping("/{crewMemberId}/flights/{flightId}/remove")
    public ResponseEntity<FlightDto> removeCrewMemberFromFlight(@PathVariable Integer crewMemberId, @PathVariable Integer flightId) {
        FlightDto alteredFlight = crewMemberService.removeCrewMemberFromFlight(crewMemberId, flightId);
        return new ResponseEntity<>(alteredFlight, HttpStatus.OK);
    }
}
