package com.java.ravito_plan.food.domain.usecase.food.createFood;

import com.java.ravito_plan.food.domain.dto.FoodCreationParams;

public record CreateFoodRequest(Long brandId, FoodCreationParams foodCreationParams) {

}
