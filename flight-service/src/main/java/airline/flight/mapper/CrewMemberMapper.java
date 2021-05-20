package airline.flight.mapper;

import airline.flight.dto.CrewMemberDto;
import airline.flight.model.CrewMember;

public class CrewMemberMapper {
    public static CrewMemberDto toCrewMemberDto(CrewMember crewMember) {
        return CrewMemberDto.builder()
                .id(crewMember.getId())
                .surname(crewMember.getSurname())
                .name(crewMember.getName())
                .gender(crewMember.getGender())
                .role(crewMember.getRole())
                .age(crewMember.getAge())
                .build();
    }
}
