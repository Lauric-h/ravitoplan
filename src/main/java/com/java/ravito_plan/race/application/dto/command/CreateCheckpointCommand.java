package com.java.ravito_plan.race.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCheckpointCommand implements CheckpointCommand {

    @NotNull
    private Long raceId;

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

    @Override
    public Long getRaceId() {
        return this.raceId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getDistanceFromStart() {
        return this.distanceFromStart;
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public Integer getEstimatedTimeInMinuteFromStart() {
        return this.estimatedTimeInMinuteFromStart;
    }

    @Override
    public int getCumulatedElevationGainFromStart() {
        return this.cumulatedElevationGainFromStart;
    }
    @Override

    public int getCumulatedElevationLossFromStart() {
        return this.cumulatedElevationLossFromStart;
    }

    @Override
    public Integer getCarbsTarget() {
        return this.carbsTarget;
    }
}
