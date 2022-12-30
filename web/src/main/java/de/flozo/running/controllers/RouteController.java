package de.flozo.running.controllers;

import de.flozo.running.services.RouteService;
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

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/show")
    public String showById(Model model) {
        model.addAttribute("routes", routeService.findAll());
        return ROUTE + SHOW;
    }


    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable String id) {
        routeService.deleteById(Long.valueOf(id));
        return REDIRECT + ROUTE + SHOW;
    }


}
