package de.flozo.running.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class RunningEvent extends BaseEntity {

    private EventType eventType;
    private Route route;
    private LocalDate date;
    private LocalTime startTime;
    private Double temperatureCelsius;
    private String remarks;

    private Set<Lap> laps;


}
