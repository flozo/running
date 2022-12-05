package de.flozo.running.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalTime;

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

    public Integer getLapNumber() {
        return lapNumber;
    }

    public void setLapNumber(Integer lapNumber) {
        this.lapNumber = lapNumber;
    }

    public LocalTime getLapTime() {
        return lapTime;
    }

    public void setLapTime(LocalTime lapTime) {
        this.lapTime = lapTime;
    }

    public Integer getAvgHeartRate() {
        return avgHeartRate;
    }

    public void setAvgHeartRate(Integer avgHeartRate) {
        this.avgHeartRate = avgHeartRate;
    }

    public Integer getMaxHeartRate() {
        return maxHeartRate;
    }

    public void setMaxHeartRate(Integer maxHeartRate) {
        this.maxHeartRate = maxHeartRate;
    }

    public Energy getEnergyBurned() {
        return energyBurned;
    }

    public void setEnergyBurned(Energy energyBurned) {
        this.energyBurned = energyBurned;
    }

    @Override
    public String toString() {
        return "Lap{" +
                "lapNumber=" + lapNumber +
                ", lapTime=" + lapTime +
                ", avgHeartRate=" + avgHeartRate +
                ", maxHeartRate=" + maxHeartRate +
                ", energyBurned=" + energyBurned +
                '}';
    }
}
