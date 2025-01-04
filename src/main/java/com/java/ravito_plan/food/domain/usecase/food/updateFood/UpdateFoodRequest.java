package com.java.ravito_plan.food.domain.usecase.food.updateFood;

import com.java.ravito_plan.food.domain.dto.FoodUpdateParams;

public record UpdateFoodRequest(Long foodId, Long brandId, FoodUpdateParams foodUpdateParams) {

}
