package de.flozo.running.converters;

import de.flozo.running.commands.LapCommand;
import de.flozo.running.model.Lap;
import de.flozo.running.model.RunningEvent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class LapCommandToLapConverter implements Converter<LapCommand, Lap> {

    public Long timeToMilliseconds(LocalTime time) {
        if (time == null) {
            return null;
        }
        int h = time.getHour();
        int m = time.getMinute();
        int s = time.getSecond();
        int ns = time.getNano();
        return (long) (ns / 1000000 + s * 1000 + m * 60000 + h * 3600000);
    }


    @Override
    public Lap convert(LapCommand source) {
//        EnergyUnit energyUnit = EnergyUnit.builder()
////                .id(source.getEnergyBurnedUnitId())
//                .build();
//        Energy energy = Energy.builder()
//                .value(source.getEnergyBurnedValue())
//                .unit(energyUnit)
//                .build();
        RunningEvent runningEvent = RunningEvent.builder()
                .id(source.getRunningEventId())
                .build();
        return Lap.builder()
                .id(source.getId())
                .lapNumber(source.getLapNumber())
                .lapTime(timeToMilliseconds(source.getLapTime()))
                .avgHeartRate(source.getAvgHeartRate())
                .maxHeartRate(source.getMaxHeartRate())
                .energyBurned(source.getEnergyBurned())
                .runningEvent(runningEvent)
                .build();

    }
}
