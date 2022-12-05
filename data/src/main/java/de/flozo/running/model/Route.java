package de.flozo.running.model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Route extends BaseEntity {

    private String name;
    private Double totalDistanceKilometers;
    private Integer numberOfLaps;


}
