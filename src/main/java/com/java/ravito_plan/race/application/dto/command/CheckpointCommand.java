package com.java.ravito_plan.race.application.dto.command;

public interface CheckpointCommand {

    Long getRaceId();
    String getName();
    int getDistanceFromStart();
    String getLocation();
    String getType();
    Integer getEstimatedTimeInMinuteFromStart();
    int getCumulatedElevationGainFromStart();
    int getCumulatedElevationLossFromStart();
    Integer getCarbsTarget();
}
