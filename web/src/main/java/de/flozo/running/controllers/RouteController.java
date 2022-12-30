package de.flozo.running.controllers;

import de.flozo.running.services.RouteService;
import de.flozo.running.services.RunningEventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("route")
@Controller
public class RouteController {

    public static final String REDIRECT = "redirect:/";
    public static final String ROUTE = "route/";
    //    public static final String RUNNING_EVENT_FORM = "runningEventForm";
    public static final String SHOW = "show";


    private final RouteService routeService;
    private final RunningEventService runningEventService;

    public RouteController(RouteService routeService, RunningEventService runningEventService) {
        this.routeService = routeService;
        this.runningEventService = runningEventService;
    }

    @GetMapping("/show")
    public String showById(Model model) {
        model.addAttribute("routes", routeService.findAll());
        return ROUTE + SHOW;
    }


    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        Long count = runningEventService.countRunningEventsByRouteId(id);
        if (count == 0) {
            routeService.deleteById(id);
        } else {
            System.out.println("Can't delete route with id " + id + ". It is used by " + count + " running events!");
        }
        return REDIRECT + ROUTE + SHOW;
    }


}
