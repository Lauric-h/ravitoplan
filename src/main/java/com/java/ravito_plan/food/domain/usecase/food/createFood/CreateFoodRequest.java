package com.java.ravito_plan.food.domain.usecase.food.createFood;

import com.java.ravito_plan.food.application.dto.command.CreateFoodCommand;

public record CreateFoodRequest(Long brandId, CreateFoodCommand command) {

}
