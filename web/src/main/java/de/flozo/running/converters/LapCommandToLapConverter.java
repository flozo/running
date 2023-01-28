package de.flozo.running.converters;

import de.flozo.running.commands.LapCommand;
import de.flozo.running.model.Energy;
import de.flozo.running.model.EnergyUnit;
import de.flozo.running.model.Lap;
import de.flozo.running.model.RunningEvent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LapCommandToLapConverter implements Converter<LapCommand, Lap> {


    @Override
    public Lap convert(LapCommand source) {
        EnergyUnit energyUnit = EnergyUnit.builder()
                .id(source.getEnergyBurnedUnitId())
                .build();
        Energy energy = Energy.builder()
                .value(source.getEnergyBurnedValue())
                .unit(energyUnit)
                .build();
        RunningEvent runningEvent = RunningEvent.builder()
                .id(source.getRunningEventId())
                .build();
        return Lap.builder()
                .lapNumber(source.getLapNumber())
                .lapTime(source.getLapTime())
                .avgHeartRate(source.getAvgHeartRate())
                .maxHeartRate(source.getMaxHeartRate())
                .energyBurned(energy)
                .runningEvent(runningEvent)
                .build();

    }
}
