package com.java.ravito_plan.race.application.mapper.view;

import com.java.ravito_plan.race.application.dto.internal.FoodDto;
import com.java.ravito_plan.race.application.dto.view.CheckpointView;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import com.java.ravito_plan.race.domain.model.FoodNutrients;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckpointViewMapper {

    public static CheckpointView toCheckpointDetailView(Checkpoint checkpoint,
            Map<Long, FoodDto> foods) {

        Map<Long, FoodNutrients> nutrients = foods.entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getKey,
                        e -> new FoodNutrients(e.getValue().carbohydrates, e.getValue().calories,
                                e.getValue().proteins)));

        return new CheckpointView(checkpoint.getName(), checkpoint.getDistanceFromStart(),
                checkpoint.getLocation(), checkpoint.getType().name(),
                checkpoint.getEstimatedTimeInMinuteFromStart(),
                checkpoint.getCumulatedElevationGainFromStart(),
                checkpoint.getCumulatedElevationLossFromStart(), checkpoint.getCarbsTarget(),
                checkpoint.calculateTotalCarbs(nutrients), checkpoint.getCheckpointFoods().stream()
                .map(cpFood -> CheckpointFoodViewMapper.toCheckpointFoodView(cpFood, foods))
                .toList());
    }
}
