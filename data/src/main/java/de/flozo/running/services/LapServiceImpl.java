package de.flozo.running.services;

import de.flozo.running.exceptions.NotFoundException;
import de.flozo.running.model.Lap;
import de.flozo.running.repositories.LapRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    public Lap findById(Long aLong) {
        Optional<Lap> lapOptional = lapRepository.findById(aLong);
        if (lapOptional.isEmpty()) {
            throw new NotFoundException("Lap not found! Id value " + aLong + " not present!");
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
    public void deleteById(Long aLong) {
        lapRepository.deleteById(aLong);
    }
}
