package de.flozo.running.converters;

import de.flozo.running.commands.RouteCommand;
import de.flozo.running.model.Route;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RouteCommandToRouteConverter implements Converter<RouteCommand, Route> {

    @Override
    public Route convert(RouteCommand source) {
        return Route.builder()
                .name(source.getRouteName())
                .location(source.getLocation())
                .totalDistanceKilometers(source.getTotalDistance())
                .numberOfLaps(source.getNumberOfLaps())
                .build();
    }
}
