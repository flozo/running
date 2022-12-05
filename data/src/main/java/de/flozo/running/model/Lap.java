package de.flozo.running.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "laps")
public class Lap extends BaseEntity {

    private Integer lapNumber;
    private LocalTime lapTime;
    private Integer avgHeartRate;
    private Integer maxHeartRate;
    private Energy energyBurned;

    public Lap(Long id, Integer lapNumber, LocalTime lapTime, Integer avgHeartRate, Integer maxHeartRate, Energy energyBurned) {
        super(id);
        this.lapNumber = lapNumber;
        this.lapTime = lapTime;
        this.avgHeartRate = avgHeartRate;
        this.maxHeartRate = maxHeartRate;
        this.energyBurned = energyBurned;
    }


}
