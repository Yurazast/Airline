package airline.crew_member.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String departurePlace;
    private String arrivalPlace;
    private String airplane;
    private Date departureTime;
    private Date arrivalTime;

    @ManyToMany(mappedBy = "flights")
    private Set<CrewMember> crew;
}
