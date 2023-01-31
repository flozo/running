package de.flozo.running.converters;

import de.flozo.running.commands.RunningEventCommand;
import de.flozo.running.model.EventType;
import de.flozo.running.model.RunningEvent;
import org.springframework.core.convert.converter.Converter;

public class RunningEventCommandToRunningEventConverter implements Converter<RunningEventCommand, RunningEvent> {

    @Override
    public RunningEvent convert(RunningEventCommand source) {
        EventType eventType = EventType.values()[Math.toIntExact(source.getEventTypeId())];
        return RunningEvent.builder()
                .id(source.getId())
                .eventType(eventType)
//                .route()
                .date(source.getDate())
                .startTime(source.getStartTime())
                .temperatureCelsius(source.getTemperature())
                .remarks(source.getRemarks())
                .build();
    }
}
