package com.java.ravito_plan.food.application.mapper;

import com.java.ravito_plan.food.application.dto.command.FoodCommand;
import com.java.ravito_plan.food.application.dto.internal.FoodDetail;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.model.IngestionType;

public class FoodMapper {
    public static FoodDetail toFoodDetail(Food food) {
        return new FoodDetail(food.getId(), food.getName(), food.getCarbohydrates(), food.getCalories(), food.getProteins(), food.isElectrolytes(), food.getLink(), food.getComment(), food.getIngestionType()
                .name());
    }

    public static Food toFood(FoodCommand foodCommand) {
        return new Food(foodCommand.getName(), foodCommand.getCarbohydrates(), foodCommand.getCalories(), foodCommand.getProteins(), foodCommand.hasElectrolytes(), foodCommand.getLink(), foodCommand.getComment(),
                IngestionType.valueOf(foodCommand.getIngestionType()));
    }
}
