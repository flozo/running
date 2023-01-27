package de.flozo.running.commands;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class RouteCommand {

    @NotBlank
    private String routeName;

    @NotBlank
    private String location;

    @Min(0)
    private Double totalDistance;

    @Min(0)
    @Max(1000)
    private Integer numberOfLaps;
    
}
