package de.flozo.running.controllers;

import de.flozo.running.services.RunningEventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final RunningEventService runningEventService;

    public IndexController(RunningEventService runningEventService) {
        this.runningEventService = runningEventService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String getIndexPage(Model model) {
//        model.addAttribute("running_events", runningEventService.findAll());
        model.addAttribute("running_events", runningEventService.findAllByOrderByDateDesc());
        return "index";
    }
}
