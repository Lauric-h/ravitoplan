package com.java.ravito_plan.race.application.mapper;

import com.java.ravito_plan.race.application.dto.ExternalFoodDto;
import com.java.ravito_plan.race.application.dto.view.CheckpointDetailView;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import java.util.Map;

public class CheckpointViewMapper {

    public static CheckpointDetailView toCheckpointDetailView(Checkpoint checkpoint,
            Map<Long, ExternalFoodDto> foods) {
        return new CheckpointDetailView(checkpoint.getName(), checkpoint.getDistanceFromStart(),
                checkpoint.getLocation(), checkpoint.getType().name(),
                checkpoint.getEstimatedTimeInMinuteFromStart(),
                checkpoint.getCumulatedElevationGainFromStart(),
                checkpoint.getCumulatedElevationLossFromStart(), checkpoint.getCarbsTarget(),
                checkpoint.getCheckpointFoods().stream()
                        .map(cpFood -> CheckpointFoodViewMapper.toCheckpointFoodView(cpFood, foods))
                        .toList());
    }
}
