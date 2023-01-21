package de.flozo.running.services;

import de.flozo.running.exceptions.NotFoundException;
import de.flozo.running.model.Lap;
import de.flozo.running.repositories.LapRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LapServiceImpl implements LapService {

    private final LapRepository lapRepository;

    public LapServiceImpl(LapRepository lapRepository) {
        this.lapRepository = lapRepository;
    }

    @Override
    public Set<Lap> findAll() {
        Set<Lap> laps = new HashSet<>();
        lapRepository.findAll().iterator().forEachRemaining(laps::add);
        return laps;
    }

    @Override
    public List<Lap> findAllByRunningEventIdOrderByLapNumberAsc(Long id) {
        List<Lap> laps = new ArrayList<>();
        lapRepository.findAllByRunningEventIdOrderByLapNumberAsc(id).iterator().forEachRemaining(laps::add);
        return laps;
    }

    @Override
    public Lap findById(Long id) {
        Optional<Lap> lapOptional = lapRepository.findById(id);
        if (lapOptional.isEmpty()) {
            throw new NotFoundException("Lap not found! Id value " + id + " not present!");
        }
        return lapOptional.get();
    }

    @Override
    public Lap save(Lap lap) {
        return lapRepository.save(lap);
    }

    @Override
    public void delete(Lap lap) {
        lapRepository.delete(lap);
    }

    @Override
    public void deleteById(Long id) {
        lapRepository.deleteById(id);
    }
}
