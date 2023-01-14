package de.flozo.running.bootstrap;

import de.flozo.running.model.EnergyUnit;
import de.flozo.running.repositories.EnergyUnitRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Profile({"dev", "prod"})
public class DataLoaderMySQL implements ApplicationListener<ContextRefreshedEvent> {

    private final EnergyUnitRepository energyUnitRepository;

    public DataLoaderMySQL(EnergyUnitRepository energyUnitRepository) {
        this.energyUnitRepository = energyUnitRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (energyUnitRepository.count() == 0L) {
            loadEnergyUnits();
        }

    }


    private void loadEnergyUnits() {

        EnergyUnit kcal = new EnergyUnit();
        kcal.setUnitName("kilocalories");
        kcal.setUnitSymbol("kcal");

        EnergyUnit kJoule = new EnergyUnit();
        kJoule.setUnitName("kilojoule");
        kJoule.setUnitSymbol("kJ");

        energyUnitRepository.save(kcal);
        energyUnitRepository.save(kJoule);
    }


}
