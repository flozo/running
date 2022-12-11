package de.flozo.running.controllers;

import de.flozo.running.model.Lap;
import de.flozo.running.model.Route;
import de.flozo.running.model.RunningEvent;
import de.flozo.running.services.LapService;
import de.flozo.running.services.RouteService;
import de.flozo.running.services.RunningEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RunningEventControllerTest {

    @Mock
    RunningEventService runningEventService;

    @Mock
    LapService lapService;

    @Mock
    RouteService routeService;

    RunningEventController runningEventController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        runningEventController = new RunningEventController(runningEventService, lapService, routeService);
        mockMvc = MockMvcBuilders.standaloneSetup(runningEventController).build();
    }

    @Test
    void showById() throws Exception {
        RunningEvent runningEvent = new RunningEvent();
        runningEvent.setId(1L);
        when(runningEventService.findById(anyLong())).thenReturn(runningEvent);

        List<Lap> laps = new ArrayList<>();
        Lap lap = new Lap();
        lap.setId(1L);
        laps.add(lap);
        when(lapService.findAllByRunningEventIdOrderByLapNumberAsc(anyLong())).thenReturn(laps);

        mockMvc.perform(get("/running_event/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("running_event/show"))
                .andExpect(model().attributeExists("running_event"))
                .andExpect(model().attributeExists("laps"));
    }

    @Test
    void updateRunningEvent() throws Exception {
        RunningEvent runningEvent = new RunningEvent();
        runningEvent.setId(1L);
        when(runningEventService.findById(anyLong())).thenReturn(runningEvent);

        List<Lap> laps = new ArrayList<>();
        Lap lap = new Lap();
        lap.setId(1L);
        laps.add(lap);
        when(lapService.findAllByRunningEventIdOrderByLapNumberAsc(anyLong())).thenReturn(laps);

        Set<Route> routes = new HashSet<>();
        Route route = new Route();
        route.setId(1L);
        routes.add(route);
        when(routeService.findAll()).thenReturn(routes);

        mockMvc.perform(get("/running_event/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("running_event/runningEventForm"))
                .andExpect(model().attributeExists("running_event"))
                .andExpect(model().attributeExists("laps"))
                .andExpect(model().attributeExists("routes"));
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(get("/running_event/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        verify(runningEventService, times(1)).deleteById(anyLong());
    }
}
