package com.java.ravito_plan.race.application.mapper.view;

import com.java.ravito_plan.race.application.dto.internal.FoodDto;
import com.java.ravito_plan.race.application.dto.view.CheckpointFoodView;
import com.java.ravito_plan.race.domain.model.CheckpointFood;
import java.util.Map;

public class CheckpointFoodViewMapper {

    public static CheckpointFoodView toCheckpointFoodView(CheckpointFood checkpointFood,
            Map<Long, FoodDto> foods) {
        FoodDto food = foods.get(checkpointFood.getFoodId());
        return new CheckpointFoodView(food.brandName, food.name, food.carbohydrates, food.calories,
                food.proteins, food.electrolytes, food.link, food.comment, food.type,
                checkpointFood.getQuantity());
    }
}
