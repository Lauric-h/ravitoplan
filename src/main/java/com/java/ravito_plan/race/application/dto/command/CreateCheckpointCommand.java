package com.java.ravito_plan.race.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateCheckpointCommand {

    @NotBlank
    private String name;

    @NotNull
    private int distanceFromStart;

    @NotBlank
    private String location;

    @NotBlank
    private String type;

    private Integer estimatedTimeInMinuteFromStart;

    @NotNull
    private int cumulatedElevationGainFromStart;

    @NotNull
    private int cumulatedElevationLossFromStart;

    private Integer carbsTarget;
}
