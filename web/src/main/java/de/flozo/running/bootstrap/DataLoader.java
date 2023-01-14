package de.flozo.running.bootstrap;

import de.flozo.running.model.*;
import de.flozo.running.services.EnergyUnitService;
import de.flozo.running.services.LapService;
import de.flozo.running.services.RouteService;
import de.flozo.running.services.RunningEventService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Profile("default")
public class DataLoader implements CommandLineRunner {

    private final LapService lapService;
    private final RouteService routeService;
    private final RunningEventService runningEventService;
    private final EnergyUnitService energyUnitService;

    public DataLoader(LapService lapService, RouteService routeService, RunningEventService runningEventService, EnergyUnitService energyUnitService) {
        this.lapService = lapService;
        this.routeService = routeService;
        this.runningEventService = runningEventService;
        this.energyUnitService = energyUnitService;
    }


    @Override
    public void run(String... args) throws Exception {
        loadData();

    }

    private void loadData() {

        // Define energy units

        EnergyUnit kcal = new EnergyUnit();
        kcal.setUnitName("kilocalories");
        kcal.setUnitSymbol("kcal");

        EnergyUnit kJoule = new EnergyUnit();
        kJoule.setUnitName("kilojoule");
        kJoule.setUnitSymbol("kJ");

        EnergyUnit savedKcal = energyUnitService.save(kcal);
        EnergyUnit savedKJoule = energyUnitService.save(kJoule);


        // Define route 1

        System.out.println("New route ...");
        Route tenKmRoute1 = new Route();
        tenKmRoute1.setLocation("City forest");
        tenKmRoute1.setName("10 km route");
        tenKmRoute1.setTotalDistanceKilometers(10.0);
        tenKmRoute1.setNumberOfLaps(3);

        System.out.println("Save route ...");
        Route savedRoute1 = routeService.save(tenKmRoute1);

        // Define route 2

        System.out.println("New route ...");
        Route tenKmRoute2 = new Route();
        tenKmRoute2.setLocation("Park");
        tenKmRoute2.setName("10 km route in park");
        tenKmRoute2.setTotalDistanceKilometers(10.0);
        tenKmRoute2.setNumberOfLaps(4);

        System.out.println("Save route ...");
        Route savedRoute2 = routeService.save(tenKmRoute2);



        // Define running event 1

        System.out.println("New running event ...");
        RunningEvent runningEvent1 = new RunningEvent();
        runningEvent1.setEventType(EventType.RACE);
        runningEvent1.setRoute(savedRoute1);
        runningEvent1.setTemperatureCelsius(1.0);
        runningEvent1.setDate(LocalDate.of(2022, 12, 4));
        runningEvent1.setStartTime(LocalTime.of(10, 0));
        runningEvent1.setRemarks("very cold");

        // Define running event 2

        System.out.println("New running event ...");
        RunningEvent runningEvent2 = new RunningEvent();
        runningEvent2.setEventType(EventType.TRAINING);
        runningEvent2.setRoute(savedRoute1);
        runningEvent2.setTemperatureCelsius(9.0);
        runningEvent2.setDate(LocalDate.of(2022, 11, 25));
        runningEvent2.setStartTime(LocalTime.of(16, 29));
        runningEvent2.setRemarks("nothing special");

        // Laps of event 1
        // Define lap 1
        System.out.println("New laps ...");
        Lap lap1 = new Lap();
        lap1.setLapNumber(1);
        lap1.setLapTime(LocalTime.of(0, 15, 29, 600000000));
        lap1.setAvgHeartRate(174);
        lap1.setMaxHeartRate(180);
        lap1.setEnergyBurned(new Energy(233.0, savedKcal));
        lap1.setRunningEvent(runningEvent1);

        // Define lap 2
        Lap lap2 = new Lap();
        lap2.setLapNumber(2);
        lap2.setLapTime(LocalTime.of(0, 16, 17, 200000000));
        lap2.setAvgHeartRate(179);
        lap2.setMaxHeartRate(181);
        lap2.setEnergyBurned(new Energy(256.0, savedKcal));
        lap2.setRunningEvent(runningEvent1);

        // Define lap 3
        Lap lap3 = new Lap();
        lap3.setLapNumber(3);
        lap3.setLapTime(LocalTime.of(0, 17, 28, 8));
        lap3.setAvgHeartRate(182);
        lap3.setMaxHeartRate(187);
        lap3.setEnergyBurned(new Energy(281.0, savedKcal));
        lap3.setRunningEvent(runningEvent1);


        // Laps of event 2
        // Define lap 1
        System.out.println("New laps ...");
        Lap lap21 = new Lap();
        lap21.setLapNumber(1);
        lap21.setLapTime(LocalTime.of(0, 16, 34, 0));
        lap21.setAvgHeartRate(173);
        lap21.setMaxHeartRate(177);
        lap21.setEnergyBurned(new Energy(247.0, savedKcal));
        lap21.setRunningEvent(runningEvent2);

        // Define lap 2
        Lap lap22 = new Lap();
        lap22.setLapNumber(2);
        lap22.setLapTime(LocalTime.of(0, 17, 36, 900000000));
        lap22.setAvgHeartRate(173);
        lap22.setMaxHeartRate(178);
        lap22.setEnergyBurned(new Energy(265.0, savedKcal));
        lap22.setRunningEvent(runningEvent2);

        Energy energy = new Energy(270.0, savedKcal);

        // Define lap 3
        Lap lap23 = new Lap();
        lap23.setLapNumber(3);
        lap23.setLapTime(LocalTime.of(0, 17, 16, 4));
        lap23.setAvgHeartRate(178);
        lap23.setMaxHeartRate(184);
        lap23.setEnergyBurned(energy);
        lap23.setRunningEvent(runningEvent2);



        runningEvent1.addLap(lap1).addLap(lap2).addLap(lap3);
        runningEvent2.addLap(lap21).addLap(lap22).addLap(lap23);


        System.out.println("Save running events ...");
        runningEventService.save(runningEvent1);
        runningEventService.save(runningEvent2);


        System.out.println("Save laps ...");
        lapService.save(lap1);
        lapService.save(lap2);
        lapService.save(lap3);
        lapService.save(lap21);
        lapService.save(lap22);
        lapService.save(lap23);

//        Set<Lap> laps = new HashSet<>();
//        laps.add(savedLap1);
//        laps.add(savedLap2);
//        laps.add(savedLap3);



//        System.out.println(runningEventService.findAll());



    }


}
