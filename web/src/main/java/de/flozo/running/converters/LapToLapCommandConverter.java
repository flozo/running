package de.flozo.running.converters;

import de.flozo.running.commands.LapCommand;
import de.flozo.running.model.Lap;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LapToLapCommandConverter implements Converter<Lap, LapCommand> {

    @Override
    public LapCommand convert(Lap source) {
        return LapCommand.builder()
                .lapNumber(source.getLapNumber())
                .lapTime(source.getLapTime())
                .avgHeartRate(source.getAvgHeartRate())
                .maxHeartRate(source.getMaxHeartRate())
                .energyBurnedValue(source.getEnergyBurned().getValue())
                .energyBurnedUnitId(source.getEnergyBurned().getUnit().getId())
                .runningEventId(source.getRunningEvent().getId())
                .build();
    }
}
