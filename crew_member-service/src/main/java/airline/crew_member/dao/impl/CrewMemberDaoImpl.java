package airline.crew_member.dao.impl;

import airline.crew_member.dao.CrewMemberDao;
import airline.crew_member.model.CrewMember;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class CrewMemberDaoImpl implements CrewMemberDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CrewMember> getAll() {
        return entityManager.createQuery("SELECT cm FROM CrewMember cm", CrewMember.class).getResultList();
    }

    @Override
    public Optional<CrewMember> getById(int id) {
        return Optional.ofNullable(entityManager.find(CrewMember.class, id));
    }

    @Transactional
    @Override
    public CrewMember save(CrewMember crewMember) {
        entityManager.persist(crewMember);
        return crewMember;
    }

    @Transactional
    @Override
    public CrewMember update(CrewMember crewMember) {
        entityManager.merge(crewMember);
        return crewMember;
    }

    @Transactional
    @Override
    public void delete(CrewMember crewMember) {
        entityManager.remove(crewMember);
    }
}
