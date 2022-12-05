package de.flozo.running.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "running_events")
public class RunningEvent extends BaseEntity {

    private EventType eventType;
    private Route route;
    private LocalDate date;
    private LocalTime startTime;
    private Double temperatureCelsius;
    private String remarks;

    @OneToMany(mappedBy = "running_event")
    private Set<Lap> laps;


}
