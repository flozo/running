package de.flozo.running.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@EqualsAndHashCode(callSuper = true)
//@Data
@Entity
//@Table(name = "laps")
public class Lap extends BaseEntity {

    @Column
    private Integer lapNumber;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @Column
    private LocalTime lapTime;

    @Column(name = "average_heart_rate")
    private Integer avgHeartRate;

    @Column(name = "maximum_heart_rate")
    private Integer maxHeartRate;

    @Column
    @Embedded
    private Energy energyBurned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "running_event_id")
    private RunningEvent runningEvent;

//    public RunningEvent addToRunningEvent(RunningEvent runningEvent) {
//
//    }

}
