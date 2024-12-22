package com.java.ravito_plan.race.application.dto.command;

public class CreateCheckpointCommand implements CheckpointCommand {

    private Long raceId;
    private String name;
    private int distanceFromStart;
    private String location;
    private String type;
    private Integer estimatedTimeInMinuteFromStart;
    private int cumulatedElevationGainFromStart;
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
