package airline.crew_member.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "crew_member")
@EqualsAndHashCode(exclude = "flights")
public class CrewMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String surname;
    private String name;
    private String gender;
    private String role;
    private Integer age;

    @ManyToMany
    @JoinTable(name = "flight_crew", joinColumns = {@JoinColumn(name = "crew_member_id")}, inverseJoinColumns = {@JoinColumn(name = "flight_id")})
    private Set<Flight> flights;
}
