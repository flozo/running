package de.flozo.running.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Entity
public class Route extends BaseEntity {

    @Column
    private String name;

    @Column
    private String location;

    @Column(name = "total_distance_km")
    private Double totalDistanceKilometers;

    @Column
    private Integer numberOfLaps;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "route")
    private Set<RunningEvent> runningEvents;

}
