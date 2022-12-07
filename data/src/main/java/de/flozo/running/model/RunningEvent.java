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
    @JoinColumn(name = "route_id")
    private Route route;

    @Column
    private LocalDate date;

    @Column
    private LocalTime startTime;

    @Column
    private Double temperatureCelsius;

    @Column
    private String remarks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "running_event")
    private Set<Lap> laps;


}
