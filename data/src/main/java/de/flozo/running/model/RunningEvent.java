package de.flozo.running.model;

import jakarta.persistence.*;
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

    @Enumerated(value = EnumType.STRING)
    private EventType eventType;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    private LocalDate date;
    private LocalTime startTime;
    private Double temperatureCelsius;
    private String remarks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "running_event")
    private Set<Lap> laps;


}
