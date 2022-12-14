package de.flozo.running.services;

import de.flozo.running.exceptions.NotFoundException;
import de.flozo.running.model.RunningEvent;
import de.flozo.running.repositories.RunningEventRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RunningEventServiceImpl implements RunningEventService {

    private final RunningEventRepository runningEventRepository;

    public RunningEventServiceImpl(RunningEventRepository runningEventRepository) {
        this.runningEventRepository = runningEventRepository;
    }

    @Override
    public Set<RunningEvent> findAll() {
        Set<RunningEvent> runningEvents = new HashSet<>();
        runningEventRepository.findAll().iterator().forEachRemaining(runningEvents::add);
        return runningEvents;

    }

    @Override
    public RunningEvent findById(Long id) {
        Optional<RunningEvent> runningEventOptional = runningEventRepository.findById(id);
        if (runningEventOptional.isEmpty()) {
            throw new NotFoundException("Running event not found! Id value " + id + " not present!");
        }
        return runningEventOptional.get();
    }

    @Override
    public List<RunningEvent> findAllByOrderByDateDesc() {
        return runningEventRepository.findAllByOrderByDateDesc();
    }

    @Override
    public RunningEvent save(RunningEvent runningEvent) {
        return runningEventRepository.save(runningEvent);
    }

    @Override
    public void delete(RunningEvent runningEvent) {
        runningEventRepository.delete(runningEvent);
    }

    @Override
    public void deleteById(Long id) {
        runningEventRepository.deleteById(id);
    }

    @Override
    public Long countRunningEventsByRouteId(Long id) {
        return runningEventRepository.countByRouteId(id);
    }
}
