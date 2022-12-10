package de.flozo.running.controllers;

import de.flozo.running.services.LapService;
import de.flozo.running.services.RunningEventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RunningEventController {

    private final RunningEventService runningEventService;
    private final LapService lapService;

    public RunningEventController(RunningEventService runningEventService, LapService lapService) {
        this.runningEventService = runningEventService;
        this.lapService = lapService;
    }


    @GetMapping("/running_event/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("running_event", runningEventService.findById(Long.valueOf(id)));
        model.addAttribute("laps", lapService.findAllByRunningEventIdOrderByLapNumberAsc(Long.valueOf(id)));
        return "running_event/show";
    }

    @GetMapping("/running_event/{id}/update")
    public String updateRunningEvent(@PathVariable String id, Model model) {
        model.addAttribute("running_event", runningEventService.findById(Long.valueOf(id)));
        model.addAttribute("laps", lapService.findAllByRunningEventIdOrderByLapNumberAsc(Long.valueOf(id)));
        return "running_event/runningEventForm";
    }


}
