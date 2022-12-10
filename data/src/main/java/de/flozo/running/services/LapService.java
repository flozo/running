package de.flozo.running.services;

import de.flozo.running.model.Lap;

import java.util.List;

public interface LapService extends CrudService<Lap, Long> {


    List<Lap> findAllByRunningEventIdOrderByLapNumberAsc(Long id);
}
