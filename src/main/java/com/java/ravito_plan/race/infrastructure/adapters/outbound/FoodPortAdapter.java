package com.java.ravito_plan.race.infrastructure.adapters.outbound;

import com.java.ravito_plan.food.application.dto.FoodDto;
import com.java.ravito_plan.food.application.service.FoodApplicationService;
import com.java.ravito_plan.race.application.dto.ExternalFoodDto;
import com.java.ravito_plan.race.domain.ports.outbound.FoodPort;
import org.springframework.stereotype.Component;

@Component
public class FoodPortAdapter implements FoodPort {

    private final FoodApplicationService foodApplicationService;
    public FoodPortAdapter(FoodApplicationService foodApplicationService) {
        this.foodApplicationService = foodApplicationService;
    }

    @Override
    public ExternalFoodDto getFoodById(Long id) {
        FoodDto foodDto = this.foodApplicationService.getFoodById(id);
        return new ExternalFoodDto(foodDto.id, foodDto.name, foodDto.carbohydrates, foodDto.calories, foodDto.proteins, foodDto.electrolytes, foodDto.link, foodDto.comment, foodDto.type);
    }
}
