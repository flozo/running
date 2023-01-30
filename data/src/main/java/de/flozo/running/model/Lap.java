package de.flozo.running.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@EqualsAndHashCode(callSuper = true)
//@Data
@SuperBuilder
@Entity
//@Table(name = "laps")
public class Lap extends BaseEntity {

    @Column
    private Integer lapNumber;

//    @Temporal(TemporalType.TIME)
//    @Column(columnDefinition = "TIME(3)")
//    @DateTimeFormat(pattern = "HH:mm:ss.SSS")
//    private LocalTime lapTime;
    @Column(name = "lap_time_milliseconds")
    private Long lapTime;
    
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
