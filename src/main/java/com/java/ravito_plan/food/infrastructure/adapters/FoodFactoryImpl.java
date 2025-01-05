package com.java.ravito_plan.food.infrastructure.adapters;

import com.java.ravito_plan.food.domain.dto.FoodCreationParams;
import com.java.ravito_plan.food.domain.dto.FoodUpdateParams;
import com.java.ravito_plan.food.domain.model.Food;
import com.java.ravito_plan.food.domain.model.IngestionType;
import com.java.ravito_plan.food.domain.ports.FoodFactory;
import org.springframework.stereotype.Component;

@Component
public class FoodFactoryImpl implements FoodFactory {

    @Override
    public Food create(FoodCreationParams foodCreationParams) {
        return new Food(foodCreationParams.name(), foodCreationParams.carbohydrates(),
                foodCreationParams.calories(), foodCreationParams.proteins(),
                foodCreationParams.electrolytes(), foodCreationParams.link(),
                foodCreationParams.comment(),
                IngestionType.valueOf(foodCreationParams.ingestionType()));
    }

    @Override
    public Food updateFields(Food food, FoodUpdateParams foodUpdateParams) {
        return food.updateFields(foodUpdateParams.name(), foodUpdateParams.carbohydrates(),
                foodUpdateParams.calories(), foodUpdateParams.proteins(),
                foodUpdateParams.electrolytes(), foodUpdateParams.link(),
                foodUpdateParams.comment(),
                IngestionType.valueOf(foodUpdateParams.ingestionType()));
    }
}
