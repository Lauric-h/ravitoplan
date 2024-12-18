package com.java.ravito_plan.food.application.mapper;

import com.java.ravito_plan.food.application.dto.FoodDto;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.model.IngestionType;

public class FoodMapper {
    public static FoodDto toFoodDto(Food food) {
        return new FoodDto(food.getId(), food.getName(), food.getCarbohydrates(), food.getCalories(), food.getProteins(), food.isElectrolytes(), food.getLink(), food.getComment(), food.getIngestionType()
                .name());
    }

    public static Food toFood(FoodDto foodDto) {
        return new Food(foodDto.name, foodDto.carbohydrates, foodDto.calories, foodDto.proteins, foodDto.electrolytes, foodDto.link, foodDto.comment,
                IngestionType.valueOf(foodDto.type));
    }
}
