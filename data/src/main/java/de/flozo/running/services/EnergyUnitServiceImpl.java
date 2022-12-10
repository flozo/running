package de.flozo.running.services;

import de.flozo.running.exceptions.NotFoundException;
import de.flozo.running.model.EnergyUnit;
import de.flozo.running.repositories.EnergyUnitRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EnergyUnitServiceImpl implements EnergyUnitService {

    private final EnergyUnitRepository energyUnitRepository;

    public EnergyUnitServiceImpl(EnergyUnitRepository energyUnitRepository) {
        this.energyUnitRepository = energyUnitRepository;
    }

    @Override
    public Set<EnergyUnit> findAll() {
        Set<EnergyUnit> energyUnits = new HashSet<>();
        energyUnitRepository.findAll().iterator().forEachRemaining(energyUnits::add);
        return energyUnits;
    }

    @Override
    public EnergyUnit findById(Long id) {
        Optional<EnergyUnit> energyUnitOptional = energyUnitRepository.findById(id);
        if (energyUnitOptional.isEmpty()) {
            throw new NotFoundException("Energy unit not found! Id value " + id + " not present!");
        }
        return energyUnitOptional.get();
    }

    @Override
    public EnergyUnit save(EnergyUnit energyUnit) {
        return energyUnitRepository.save(energyUnit);
    }

    @Override
    public void delete(EnergyUnit energyUnit) {
        energyUnitRepository.delete(energyUnit);
    }

    @Override
    public void deleteById(Long id) {
        energyUnitRepository.deleteById(id);
    }
}
