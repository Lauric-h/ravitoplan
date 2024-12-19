package com.java.ravito_plan.race.infrastructure.adapters.outbound;

import com.java.ravito_plan.food.application.dto.internal.FoodDetail;
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
        FoodDetail foodDetail = this.foodApplicationService.getFoodById(id);
        return new ExternalFoodDto(foodDetail.id, foodDetail.name, foodDetail.carbohydrates, foodDetail.calories, foodDetail.proteins, foodDetail.electrolytes, foodDetail.link, foodDetail.comment, foodDetail.type);
    }
}
