package de.flozo.running.services;

import de.flozo.running.model.Route;

public interface RouteService extends CrudService<Route, Long> {


    Route findByName(String name);
}
