package de.flozo.running.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Route extends BaseEntity {

    private String name;
    private String location;
    private Double totalDistanceKilometers;
    private Integer numberOfLaps;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "route")
    private Set<RunningEvent> runningEvents;

}
