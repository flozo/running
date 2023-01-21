package de.flozo.running.controllers;

import de.flozo.running.model.EnergyUnit;
import de.flozo.running.model.Lap;
import de.flozo.running.model.RunningEvent;
import de.flozo.running.services.EnergyUnitService;
import de.flozo.running.services.LapService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class LapControllerTest {

    @Mock
    LapService lapService;

    @Mock
    RunningEventService runningEventService;

    @Mock
    EnergyUnitService energyUnitService;

    LapController lapController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        lapController = new LapController(lapService, runningEventService, energyUnitService);
        mockMvc = MockMvcBuilders.standaloneSetup(lapController).build();
    }

    @Test
    void newLap() throws Exception {
        RunningEvent runningEvent = new RunningEvent();
        runningEvent.setId(1L);
        EnergyUnit energyUnit = new EnergyUnit();
        energyUnit.setId(1L);
        when(runningEventService.findById(anyLong())).thenReturn(runningEvent);
        when(energyUnitService.findById(anyLong())).thenReturn(energyUnit);

        mockMvc.perform(get("/running_event/1/lap/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("lap/lapForm"))
                .andExpect(model().attributeExists("lap"))
                .andExpect(model().attributeExists("energyUnits"));

    }

    @Test
    void updateLap() throws Exception {
        Lap lap = new Lap();
        lap.setId(1L);
        EnergyUnit energyUnit = new EnergyUnit();
        energyUnit.setId(1L);
        Set<EnergyUnit> energyUnits = new HashSet<>();
        energyUnits.add(energyUnit);
        when(lapService.findById(anyLong())).thenReturn(lap);
        when(energyUnitService.findAll()).thenReturn(energyUnits);

        mockMvc.perform(get("/running_event/1/lap/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("lap/lapForm"))
                .andExpect(model().attributeExists("lap"))
                .andExpect(model().attributeExists("energyUnits"));
    }

    @Test
    void processUpdateLapForm() throws Exception {
        RunningEvent runningEvent = new RunningEvent();
        runningEvent.setId(1L);
        when(runningEventService.findById(anyLong())).thenReturn(runningEvent);

        Lap lap = new Lap();
        lap.setId(1L);

        when(lapService.save(ArgumentMatchers.any())).thenReturn(lap);

        when(runningEventService.save(ArgumentMatchers.any())).thenReturn(runningEvent);

        mockMvc.perform(post("/running_event/1/lap/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/running_event/1/show"));

        verify(runningEventService).save(ArgumentMatchers.any());
    }

    @Test
    void deleteLap() throws Exception {
        mockMvc.perform(get("/running_event/1/lap/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/running_event/1/show"));
        verify(lapService,times(1)).deleteById(anyLong());
    }
}
