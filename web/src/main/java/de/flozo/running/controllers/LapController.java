package de.flozo.running.controllers;

import de.flozo.running.model.Energy;
import de.flozo.running.model.EnergyUnit;
import de.flozo.running.model.Lap;
import de.flozo.running.model.RunningEvent;
import de.flozo.running.services.EnergyUnitService;
import de.flozo.running.services.LapService;
import de.flozo.running.services.RunningEventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("running_event")
@Controller
public class LapController {

    public static final String REDIRECT = "redirect:/";
    public static final String RUNNING_EVENT = "running_event/";
    public static final String LAP = "lap/";
    public static final String LAP_FORM = "lapForm";
    public static final String SHOW = "show";

    private final LapService lapService;
    private final RunningEventService runningEventService;
    private final EnergyUnitService energyUnitService;


    public LapController(LapService lapService, RunningEventService runningEventService, EnergyUnitService energyUnitService) {
        this.lapService = lapService;
        this.runningEventService = runningEventService;
        this.energyUnitService = energyUnitService;
    }

    @GetMapping("/{runningEventId}/lap/new")
    public String newLap(@PathVariable Long runningEventId, Model model) {
        Lap lap = new Lap();
        lap.setRunningEvent(runningEventService.findById(runningEventId));
        model.addAttribute("lap", lap);
        model.addAttribute("energyUnit", energyUnitService.findAll());
        return LAP + LAP_FORM;
    }

    @GetMapping("/{runningEventId}/lap/{lapId}/update")
    public String updateLap(@PathVariable Long runningEventId, @PathVariable Long lapId, Model model) {
        model.addAttribute("lap", lapService.findById(lapId));
        model.addAttribute("energyUnit", energyUnitService.findAll());
        return LAP + LAP_FORM;
    }


    @PostMapping("/{runningEventId}/lap/")
    public String processUpdateLapForm(@PathVariable Long runningEventId, @ModelAttribute Lap lap, @ModelAttribute EnergyUnit energyUnit) {
        RunningEvent runningEvent = runningEventService.findById(runningEventId);
        lap.setRunningEvent(runningEvent);
        EnergyUnit savedEnergyUnit = energyUnitService.save(energyUnit);
        Energy energy = new Energy();
        energy.setValue(lap.getEnergyBurned().getValue());
        energy.setUnit(savedEnergyUnit);
        lap.setEnergyBurned(energy);
        Lap savedLap = lapService.save(lap);
        runningEvent.addLap(savedLap);
        RunningEvent savedRunningEvent = runningEventService.save(runningEvent);
        return REDIRECT + RUNNING_EVENT + savedRunningEvent.getId() + "/" + SHOW;
    }

}
