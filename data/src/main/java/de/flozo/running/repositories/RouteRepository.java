package de.flozo.running.repositories;

import de.flozo.running.model.Route;
import org.springframework.data.repository.CrudRepository;

public interface RouteRepository extends CrudRepository<Route, Long> {

    Route findByName(String name);

}
