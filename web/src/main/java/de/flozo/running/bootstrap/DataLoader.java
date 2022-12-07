package de.flozo.running.bootstrap;

import de.flozo.running.model.*;
import de.flozo.running.services.LapService;
import de.flozo.running.services.RouteService;
import de.flozo.running.services.RunningEventService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final LapService lapService;
    private final RouteService routeService;
    private final RunningEventService runningEventService;

    public DataLoader(LapService lapService, RouteService routeService, RunningEventService runningEventService) {
        this.lapService = lapService;
        this.routeService = routeService;
        this.runningEventService = runningEventService;
    }


    @Override
    public void run(String... args) throws Exception {
        loadData();

    }

    private void loadData() {

        System.out.println("New route ...");
        Route tenKmRoute1 = new Route();
        tenKmRoute1.setLocation("City forest");
        tenKmRoute1.setName("10 km route");
        tenKmRoute1.setTotalDistanceKilometers(10.0);
        tenKmRoute1.setNumberOfLaps(3);

        System.out.println("Save route ...");
        Route savedRoute = routeService.save(tenKmRoute1);


        System.out.println("New laps ...");
        Lap lap1 = new Lap();
        lap1.setLapNumber(1);
        lap1.setLapTime(LocalTime.of(0, 15, 29, 600000000));
        lap1.setAvgHeartRate(174);
        lap1.setMaxHeartRate(180);
        lap1.setEnergyBurned(new Energy(233.0, EnergyUnit.KILO_CALORIES));

        Lap lap2 = new Lap();
        lap2.setLapNumber(2);
        lap2.setLapTime(LocalTime.of(0, 16, 17, 200000000));
        lap2.setAvgHeartRate(179);
        lap2.setMaxHeartRate(181);
        lap2.setEnergyBurned(new Energy(256.0, EnergyUnit.KILO_CALORIES));

        Lap lap3 = new Lap();
        lap3.setLapNumber(3);
        lap3.setLapTime(LocalTime.of(0, 17, 28, 800000000));
        lap3.setAvgHeartRate(182);
        lap3.setMaxHeartRate(187);
        lap3.setEnergyBurned(new Energy(281.0, EnergyUnit.KILO_CALORIES));


        Set<Lap> laps = new HashSet<>();
        laps.add(lap1);
        laps.add(lap2);
        laps.add(lap3);

        System.out.println("New running event ...");
        RunningEvent runningEvent = new RunningEvent();
        runningEvent.setEventType(EventType.TRAINING);
        runningEvent.setRoute(savedRoute);
        runningEvent.setTemperatureCelsius(1.0);
        runningEvent.setDate(LocalDate.of(2022, 12, 4));
        runningEvent.setStartTime(LocalTime.of(10, 0));
        runningEvent.setRemarks("very cold");
        runningEvent.setLaps(laps);


        System.out.println("Add running event to laps ...");
        lap1.setRunningEvent(runningEvent);
        lap2.setRunningEvent(runningEvent);
        lap3.setRunningEvent(runningEvent);

        System.out.println("Save laps ...");
//        Lap savedLap1 = lapService.save(lap1);
//        Lap savedLap2 = lapService.save(lap2);
//        Lap savedLap3 = lapService.save(lap3);

        System.out.println("Save running event ...");
//        RunningEvent savedRunningEvent = runningEventService.save(runningEvent);


    }


}
