package airline.crew_member.dao;

import airline.crew_member.model.CrewMember;

import java.util.List;
import java.util.Optional;

public interface CrewMemberDao {
    List<CrewMember> getAll();
    Optional<CrewMember> getById(int id);
    CrewMember save(CrewMember crewMember);
    CrewMember update(CrewMember crewMember);
    void delete(CrewMember crewMember);
}
