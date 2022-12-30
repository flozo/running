package de.flozo.running.services;

import de.flozo.running.model.RunningEvent;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RunningEventService extends CrudService<RunningEvent, Long> {

    List<RunningEvent> findAllByOrderByDateDesc();

    @Query("SELECT COUNT(*) FROM RUNNING_EVENT WHERE ROUTE_ID=:id")
    Long countRunningEventsByRouteId(Long id);
}
