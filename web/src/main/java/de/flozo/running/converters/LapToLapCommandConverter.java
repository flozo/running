package de.flozo.running.converters;

import de.flozo.running.commands.LapCommand;
import de.flozo.running.model.Lap;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class LapToLapCommandConverter implements Converter<Lap, LapCommand> {

    private LocalTime longToTime(Long milliseconds) {
        return LocalTime.ofNanoOfDay(milliseconds * 1000);
    }


    @Override
    public LapCommand convert(Lap source) {
        return LapCommand.builder()
                .id(source.getId())
                .lapNumber(source.getLapNumber())
                .lapTime(longToTime(source.getLapTime()))
                .avgHeartRate(source.getAvgHeartRate())
                .maxHeartRate(source.getMaxHeartRate())
                .energyBurnedValue(source.getEnergyBurned().getValue())
                .energyBurnedUnitId(source.getEnergyBurned().getUnit().getId())
                .runningEventId(source.getRunningEvent().getId())
                .build();
    }
}
