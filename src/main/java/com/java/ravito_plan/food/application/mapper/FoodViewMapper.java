package com.java.ravito_plan.food.application.mapper;

import com.java.ravito_plan.food.application.dto.view.FoodSummaryView;
import com.java.ravito_plan.food.application.dto.view.FoodView;
import com.java.ravito_plan.food.domain.model.Food;

public class FoodViewMapper {

    public static FoodView toFoodView(Food food) {
        return new FoodView(food.getName(), food.getCarbohydrates(), food.getCalories(),
                food.getProteins(), food.isElectrolytes(), food.getLink(), food.getComment(),
                food.getIngestionType().name());
    }

    public static FoodSummaryView toFoodSummaryView(Food food) {
        return new FoodSummaryView(food.getName(), food.getIngestionType().name());
    }
}
