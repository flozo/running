package de.flozo.running.converters;

import de.flozo.running.commands.RouteCommand;
import de.flozo.running.model.Route;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RouteToRouteCommandConverter implements Converter<Route, RouteCommand> {

    @Override
    public RouteCommand convert(Route source) {
        return RouteCommand.builder()
                .routeName(source.getName())
                .location(source.getLocation())
                .totalDistance(source.getTotalDistanceKilometers())
                .numberOfLaps(source.getNumberOfLaps())
                .build();
    }
}
