package com.java.ravito_plan.race.domain.dto;

import com.java.ravito_plan.race.domain.model.CheckpointType;

public record CheckpointParams(String name, int distanceFromStart, String location,
                               CheckpointType checkpointType,
                               Integer estimatedTimeInMinuteFromStart,
                               int cumulatedElevationGainFromStart,
                               int cumulatedElevationLossFromStart, Integer carbsTarget) {

}
