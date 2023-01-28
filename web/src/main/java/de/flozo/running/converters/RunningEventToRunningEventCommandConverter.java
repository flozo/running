package de.flozo.running.converters;

import de.flozo.running.commands.RunningEventCommand;
import de.flozo.running.model.RunningEvent;
import org.springframework.core.convert.converter.Converter;

public class RunningEventToRunningEventCommandConverter implements Converter<RunningEvent, RunningEventCommand> {

    @Override
    public RunningEventCommand convert(RunningEvent source) {
        return RunningEventCommand.builder()
                .eventTypeId(source.getId())
                .date(source.getDate())
                .startTime(source.getStartTime())
                .temperature(source.getTemperatureCelsius())
                .routeId(source.getRoute().getId())
                .remarks(source.getRemarks())
                .build();
    }
}
