package com.java.ravito_plan.race.application.dto.view;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CheckpointView {

    public String name;
    public int distanceFromStart;
    public String location;
    public String type;
    public Integer estimatedTimeInMinuteFromStart;
    public int cumulatedElevationGainFromStart;
    public int cumulatedElevationLossFromStart;
    public Integer carbsTarget;
    public int currentTotalCarbs;
    public List<CheckpointFoodView> foods;
}
