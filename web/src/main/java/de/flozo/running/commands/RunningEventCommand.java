package de.flozo.running.commands;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class RunningEventCommand {

    @NotNull
    @Min(1)
    private Long eventTypeId;

    private LocalDate date;

    private LocalTime startTime;

    private Double temperature;

    @NotNull
    @Min(1)
    private Long routeId;

    private String remarks;
}
