package com.java.ravito_plan.race.application.dto;

import com.java.ravito_plan.race.domain.model.CheckpointType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CheckpointDto {

    public String name;
    public int distanceFromStart;
    public String location;
    public CheckpointType type;
    public Integer estimatedTimeInMinuteFromStart;
    public int cumulatedElevationGainFromStart;
    public int cumulatedElevationLossFromStart;
    public Integer carbsTarget;
}
