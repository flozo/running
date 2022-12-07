package de.flozo.running.model;

import jakarta.persistence.*;
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

    @Column
    private Integer lapNumber;

    @Column
    private LocalTime lapTime;

    @Column(name = "average_heart_rate")
    private Integer avgHeartRate;

    @Column(name = "maximum_heart_rate")
    private Integer maxHeartRate;

    @Column
    @Embedded
    private Energy energyBurned;

    @ManyToOne
    @JoinColumn(name = "running_event_id")
    private RunningEvent runningEvent;


}
