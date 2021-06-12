package airline.crew_member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
public class CrewMemberDto implements Serializable {
    private Integer id;
    private String surname;
    private String name;
    private String gender;
    private String role;
    private Integer age;
    @JsonIgnore
    private Set<FlightDto> flights;
}
