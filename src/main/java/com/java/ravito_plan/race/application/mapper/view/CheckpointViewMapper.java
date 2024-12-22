package com.java.ravito_plan.race.application.mapper.view;

import com.java.ravito_plan.race.application.dto.internal.FoodDto;
import com.java.ravito_plan.race.application.dto.view.CheckpointView;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import java.util.Map;

public class CheckpointViewMapper {

    public static CheckpointView toCheckpointDetailView(Checkpoint checkpoint,
            Map<Long, FoodDto> foods) {
        return new CheckpointView(checkpoint.getName(), checkpoint.getDistanceFromStart(),
                checkpoint.getLocation(), checkpoint.getType().name(),
                checkpoint.getEstimatedTimeInMinuteFromStart(),
                checkpoint.getCumulatedElevationGainFromStart(),
                checkpoint.getCumulatedElevationLossFromStart(), checkpoint.getCarbsTarget(),
                checkpoint.getCheckpointFoods().stream()
                        .map(cpFood -> CheckpointFoodViewMapper.toCheckpointFoodView(cpFood, foods))
                        .toList());
    }
}
