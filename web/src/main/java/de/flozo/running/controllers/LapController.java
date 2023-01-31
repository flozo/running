package de.flozo.running.controllers;

import de.flozo.running.commands.LapCommand;
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
    public static final String SHOW = "/show";

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
        EnergyUnit defaultUnit = energyUnitService.findById(1L);
        Energy defaultEnergy = Energy.builder()
                .unit(defaultUnit)
                .build();
        LapCommand lapCommand = LapCommand.builder()
                .runningEventId(runningEventId)
                .energyBurned(defaultEnergy)
                .build();
        model.addAttribute("lapCommand", lapCommand);

//        Lap lap = new Lap();
//        lap.setRunningEvent(runningEventService.findById(runningEventId));
//        EnergyUnit defaultUnit = energyUnitService.findById(1L);
//        Energy defaultEnergy = new Energy();
//        defaultEnergy.setUnit(defaultUnit);
//        lap.setEnergyBurned(defaultEnergy);
//        model.addAttribute("lap", lap);
        model.addAttribute("energyUnits", energyUnitService.findAll());
        return LAP + LAP_FORM;
    }

    @GetMapping("/{runningEventId}/lap/{lapId}/update")
    public String updateLap(@PathVariable Long runningEventId, @PathVariable Long lapId, Model model) {
        model.addAttribute("lap", lapService.findById(lapId));
        model.addAttribute("energyUnits", energyUnitService.findAll());
        return LAP + LAP_FORM;
    }


    @PostMapping("/{runningEventId}/lap/")
    public String processUpdateLapForm(@PathVariable Long runningEventId, @ModelAttribute Lap lap) {
        RunningEvent runningEvent = runningEventService.findById(runningEventId);
        lap.setRunningEvent(runningEvent);
        Lap savedLap = lapService.save(lap);
        runningEvent.addLap(savedLap);
        RunningEvent savedRunningEvent = runningEventService.save(runningEvent);
        return REDIRECT + RUNNING_EVENT + savedRunningEvent.getId() + SHOW;
    }

    @GetMapping("/{runningEventId}/lap/{lapId}/delete")
    public String deleteLap(@PathVariable Long runningEventId, @PathVariable Long lapId) {
        lapService.deleteById(lapId);
        return REDIRECT + RUNNING_EVENT + runningEventId + SHOW;
    }

}
