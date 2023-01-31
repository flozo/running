package de.flozo.running.controllers;

import de.flozo.running.commands.RouteCommand;
import de.flozo.running.converters.RouteCommandToRouteConverter;
import de.flozo.running.converters.RouteToRouteCommandConverter;
import de.flozo.running.model.Route;
import de.flozo.running.services.RouteService;
import de.flozo.running.services.RunningEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RouteControllerTest {

    @Mock
    RouteService routeService;

    @Mock
    RunningEventService runningEventService;

    @Mock
    RouteCommandToRouteConverter routeCommandToRouteConverter;

    @Mock
    RouteToRouteCommandConverter routeToRouteCommandConverter;

    RouteController routeController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        routeController = new RouteController(routeService, runningEventService, routeCommandToRouteConverter, routeToRouteCommandConverter);
        mockMvc = MockMvcBuilders.standaloneSetup(routeController).build();
    }

    @Test
    void showById() throws Exception {
        Set<Route> routes = new HashSet<>();
        Route route = new Route();
        route.setId(1L);
        routes.add(route);
        when(routeService.findAll()).thenReturn(routes);

        mockMvc.perform(get("/route/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("route/show"))
                .andExpect(model().attributeExists("routes"));
    }

    @Test
    void newRoute() throws Exception {
        mockMvc.perform(get("/route/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("route/routeForm"))
                .andExpect(model().attributeExists("route"));
    }

    @Test
    void createOrUpdate() throws Exception {
        Route route = Route.builder().id(1L).build();
        when(routeService.findById(anyLong())).thenReturn(route);

        RouteCommand routeCommand = RouteCommand.builder().id(1L).build();
        when(routeToRouteCommandConverter.convert(any())).thenReturn(routeCommand);

        mockMvc.perform(get("/route/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("route/routeForm"))
                .andExpect(model().attributeExists("routeCommand"));
    }

    @Test
    void processRouteForm() throws Exception {
        Route route = Route.builder().id(1L).build();
        when(routeService.save(ArgumentMatchers.any())).thenReturn(route);

        RouteCommand routeCommand = RouteCommand.builder().id(1L).build();
        when(routeCommandToRouteConverter.convert(routeCommand)).thenReturn(route);

        mockMvc.perform(post("/route/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/route/show"));

        verify(routeService).save(ArgumentMatchers.any());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(get("/route/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/route/show"));
        verify(routeService, times(1)).deleteById(anyLong());
    }
}
