package de.flozo.running.controllers;

import de.flozo.running.commands.RouteCommand;
import de.flozo.running.converters.RouteCommandToRouteConverter;
import de.flozo.running.converters.RouteToRouteCommandConverter;
import de.flozo.running.model.Route;
import de.flozo.running.services.RouteService;
import de.flozo.running.services.RunningEventService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("route")
@Controller
public class RouteController {

    public static final String REDIRECT = "redirect:/";
    public static final String ROUTE = "route/";
    public static final String ROUTE_FORM = "routeForm";
    public static final String SHOW = "show";


    private final RouteService routeService;
    private final RunningEventService runningEventService;
    private final RouteCommandToRouteConverter routeCommandToRouteConverter;
    private final RouteToRouteCommandConverter routeToRouteCommandConverter;

    public RouteController(RouteService routeService, RunningEventService runningEventService, RouteCommandToRouteConverter routeCommandToRouteConverter, RouteToRouteCommandConverter routeToRouteCommandConverter) {
        this.routeService = routeService;
        this.runningEventService = runningEventService;
        this.routeCommandToRouteConverter = routeCommandToRouteConverter;
        this.routeToRouteCommandConverter = routeToRouteCommandConverter;
    }

    @GetMapping("/show")
    public String showById(Model model) {
        model.addAttribute("routes", routeService.findAll());
        return ROUTE + SHOW;
    }

    @GetMapping("/new")
    public String newRoute(Model model) {
        model.addAttribute("routeCommand", new RouteCommand());
        return ROUTE + ROUTE_FORM;
    }


    @GetMapping("/{id}/update")
    public String createOrUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("routeCommand", routeToRouteCommandConverter.convert(routeService.findById(id)));
        return ROUTE + ROUTE_FORM;
    }

    @PostMapping("/")
    public String processRouteForm(@Valid @ModelAttribute("routeCommand") RouteCommand routeCommand,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return ROUTE + ROUTE_FORM;
        }
        Route route = routeCommandToRouteConverter.convert(routeCommand);
        Route savedRoute = routeService.save(route);
        return REDIRECT + ROUTE + SHOW;
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
