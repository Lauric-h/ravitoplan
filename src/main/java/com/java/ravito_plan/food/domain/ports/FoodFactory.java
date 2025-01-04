package com.java.ravito_plan.food.domain.ports;

import com.java.ravito_plan.food.domain.dto.FoodCreationParams;
import com.java.ravito_plan.food.domain.dto.FoodUpdateParams;
import com.java.ravito_plan.food.domain.model.Food;

public interface FoodFactory {

    Food create(FoodCreationParams foodCreationParams);
    Food updateFields(Food food, FoodUpdateParams foodUpdateParams);
}
