package de.flozo.running.repositories;

import de.flozo.running.model.Lap;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LapRepository extends CrudRepository<Lap, Long> {

    List<Lap> findAllByRunningEventIdOrderByLapNumberAsc(Long id);
}
