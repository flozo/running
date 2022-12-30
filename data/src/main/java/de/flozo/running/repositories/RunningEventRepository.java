package de.flozo.running.repositories;

import de.flozo.running.model.RunningEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RunningEventRepository extends CrudRepository<RunningEvent, Long> {

    List<RunningEvent> findAllByOrderByDateDesc();

    @Query("SELECT COUNT(*) FROM RUNNING_EVENT WHERE ROUTE_ID=:id")
    Long countRunningEventsByRouteId(Long id);
}
