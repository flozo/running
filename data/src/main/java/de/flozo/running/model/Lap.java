package de.flozo.running.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "laps")
public class Lap extends BaseEntity {

    private Integer lapNumber;
    private LocalTime lapTime;
    private Integer avgHeartRate;
    private Integer maxHeartRate;
    private Energy energyBurned;

    @ManyToOne
    @JoinColumn(name = "running_event_id", nullable = false)
    private RunningEvent runningEvent;


}
