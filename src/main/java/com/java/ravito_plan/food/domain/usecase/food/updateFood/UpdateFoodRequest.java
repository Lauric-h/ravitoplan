package com.java.ravito_plan.food.domain.usecase.food.updateFood;

import com.java.ravito_plan.food.application.dto.command.UpdateFoodCommand;

public record UpdateFoodRequest(Long foodId, Long brandId, UpdateFoodCommand command) {

}
