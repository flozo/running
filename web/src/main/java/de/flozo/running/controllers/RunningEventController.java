package de.flozo.running.controllers;

import de.flozo.running.services.RunningEventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RunningEventController {

    private final RunningEventService runningEventService;

    public RunningEventController(RunningEventService runningEventService) {
        this.runningEventService = runningEventService;
    }


    @GetMapping("/running_event/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("running_event", runningEventService.findById(Long.valueOf(id)));
        return "running_event/show";
    }

}
