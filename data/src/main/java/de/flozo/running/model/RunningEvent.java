package de.flozo.running.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
//@EqualsAndHashCode(callSuper = true)
//@Data
@SuperBuilder
@Entity
//@Table(name = "running_events")
public class RunningEvent extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    private EventType eventType;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    private LocalDate date;

    @Column
    private LocalTime startTime;

    @Column
    private Double temperatureCelsius;

    @Column
    private String remarks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "runningEvent")
    private Set<Lap> laps = new HashSet<>();

    public RunningEvent addLap(Lap lap) {
        laps.add(lap);
        lap.setRunningEvent(this);
        return this;
    }

    public void removeLap(Lap lap) {
        laps.remove(lap);
        lap.setRunningEvent(null);
    }


}
