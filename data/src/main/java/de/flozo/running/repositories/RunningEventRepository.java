package de.flozo.running.repositories;

import de.flozo.running.model.RunningEvent;
import org.springframework.data.repository.CrudRepository;

public interface RunningEventRepository extends CrudRepository<RunningEvent, Long> {


}
