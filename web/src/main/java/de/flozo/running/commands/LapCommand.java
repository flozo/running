package de.flozo.running.commands;

import de.flozo.running.model.Energy;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LapCommand {

    private Long id;

    @NotBlank
    @Min(1)
    @Max(1000)
    private Integer lapNumber;


    @NotBlank
    private LocalTime lapTime;

    @Min(30)
    @Max(300)
    private Integer avgHeartRate;

    @Min(30)
    @Max(300)
    private Integer maxHeartRate;

//    private Double energyBurnedValue;

//    @NotNull
//    @Min(1)
    private Energy energyBurned;

    @NotNull
    @Min(1)
    private Long runningEventId;


}
