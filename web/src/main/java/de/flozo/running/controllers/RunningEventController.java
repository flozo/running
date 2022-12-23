package de.flozo.running.controllers;

import de.flozo.running.model.RunningEvent;
import de.flozo.running.services.LapService;
import de.flozo.running.services.RouteService;
import de.flozo.running.services.RunningEventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("running_event")
@Controller
public class RunningEventController {

    public static final String REDIRECT = "redirect:/";
    public static final String RUNNING_EVENT = "running_event/";
    public static final String RUNNING_EVENT_FORM = "runningEventForm";
    public static final String SHOW = "show";

    private final RunningEventService runningEventService;
    private final LapService lapService;
    private final RouteService routeService;

    public RunningEventController(RunningEventService runningEventService,
                                  LapService lapService,
                                  RouteService routeService) {
        this.runningEventService = runningEventService;
        this.lapService = lapService;
        this.routeService = routeService;
    }

//    @InitBinder
//    public void setAllowedFields(WebDataBinder dataBinder) {
//        dataBinder.setDisallowedFields("id");
//    }

    @GetMapping("/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("running_event", runningEventService.findById(Long.valueOf(id)));
        model.addAttribute("laps", lapService.findAllByRunningEventIdOrderByLapNumberAsc(Long.valueOf(id)));
        return RUNNING_EVENT + SHOW;
    }

    @GetMapping("/new")
    public String newRunningEvent(Model model) {
        model.addAttribute("running_event", new RunningEvent());
        model.addAttribute("routes", routeService.findAll());
        return RUNNING_EVENT + RUNNING_EVENT_FORM;
    }


    @GetMapping("/{id}/update")
    public String updateRunningEvent(@PathVariable String id, Model model) {
        model.addAttribute("running_event", runningEventService.findById(Long.valueOf(id)));
        model.addAttribute("laps", lapService.findAllByRunningEventIdOrderByLapNumberAsc(Long.valueOf(id)));
        model.addAttribute("routes", routeService.findAll());
        return RUNNING_EVENT + RUNNING_EVENT_FORM;
    }

    @PostMapping("/")
    public String processUpdateRunningEventForm(@ModelAttribute RunningEvent runningEvent) {
//        if (bindingResult.hasErrors()) {
//            bindingResult.getAllErrors().forEach(objectError ->
//                    log.debug(objectError.toString())
//            );
//            System.out.println("************************");
//            return RUNNING_EVENT + "runningEventForm";
//        }
        RunningEvent savedRunningEvent = runningEventService.save(runningEvent);
        return REDIRECT + RUNNING_EVENT + savedRunningEvent.getId() + "/" + SHOW;
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable String id) {
        runningEventService.deleteById(Long.valueOf(id));
        return REDIRECT;
    }


}
