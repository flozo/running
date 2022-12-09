package de.flozo.running.controllers;

import de.flozo.running.model.RunningEvent;
import de.flozo.running.services.RunningEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {


    @Mock
    RunningEventService runningEventService;

    @Mock
    Model model;

    IndexController indexController;


    @BeforeEach
    void setUp() {
        indexController = new IndexController(runningEventService);
    }

    @Test
    void getIndexPage() {
        RunningEvent runningEvent = new RunningEvent();
        runningEvent.setId(1L);

        Set<RunningEvent> runningEvents = new HashSet<>();
        runningEvents.add(runningEvent);

        when(runningEventService.findAll()).thenReturn(runningEvents);

        String indexPageName = indexController.getIndexPage(model);
        assertEquals("index", indexPageName);

        verify(runningEventService, times(1)).findAll();
    }
}
