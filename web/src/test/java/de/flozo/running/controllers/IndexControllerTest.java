package de.flozo.running.controllers;

import de.flozo.running.model.RunningEvent;
import de.flozo.running.services.RunningEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

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

        List<RunningEvent> runningEvents = new ArrayList<>();
        runningEvents.add(runningEvent);

        when(runningEventService.findAllByOrderByDateDesc()).thenReturn(runningEvents);

        String indexPageName = indexController.getIndexPage(model);
        assertEquals("index", indexPageName);

        verify(runningEventService, times(1)).findAllByOrderByDateDesc();
    }
}
