package de.flozo.running.commands;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RouteCommand {

    private Long id;

    @NotBlank
    private String routeName;

    @NotBlank
    private String location;

    @Digits(integer = 5, fraction = 3)
    @DecimalMin("0.0")
    private Double totalDistance;

    @Min(0)
    @Max(1000)
    private Integer numberOfLaps;

}
