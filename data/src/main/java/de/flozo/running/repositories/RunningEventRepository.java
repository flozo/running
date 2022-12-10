package de.flozo.running.repositories;

import de.flozo.running.model.RunningEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RunningEventRepository extends CrudRepository<RunningEvent, Long> {

    List<RunningEvent> findAllByOrderByDateDesc();

}
