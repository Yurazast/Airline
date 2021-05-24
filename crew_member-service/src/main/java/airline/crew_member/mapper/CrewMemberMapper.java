package airline.crew_member.mapper;

import airline.crew_member.dto.CrewMemberDto;
import airline.crew_member.model.CrewMember;

public class CrewMemberMapper {
    public static CrewMember toCrewMember(CrewMemberDto crewMemberDto) {
        return CrewMember.builder()
                .surname(crewMemberDto.getSurname())
                .name(crewMemberDto.getName())
                .gender(crewMemberDto.getGender())
                .role(crewMemberDto.getRole())
                .age(crewMemberDto.getAge())
                .build();
    }

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

    public static CrewMember update(CrewMember oldCrewMember, CrewMemberDto newCrewMember) {
        oldCrewMember.setSurname(newCrewMember.getSurname());
        oldCrewMember.setName(newCrewMember.getName());
        oldCrewMember.setGender(newCrewMember.getGender());
        oldCrewMember.setRole(newCrewMember.getRole());
        oldCrewMember.setAge(newCrewMember.getAge());
        return oldCrewMember;
    }
}
