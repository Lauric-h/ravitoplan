package com.java.ravito_plan.food.infrastructure.adapters.outbound;

import com.java.ravito_plan.food.application.dto.internal.FoodDetail;
import com.java.ravito_plan.food.application.service.FoodApplicationService;
import com.java.ravito_plan.race.domain.ports.outbound.FoodPort;
import com.java.ravito_plan.race.application.dto.internal.FoodDto;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class FoodPortAdapter implements FoodPort {

    private final FoodApplicationService foodApplicationService;

    public FoodPortAdapter(FoodApplicationService foodApplicationService) {
        this.foodApplicationService = foodApplicationService;
    }

    @Override
    public FoodDto getFoodById(Long id) {
        FoodDetail foodDetail = this.foodApplicationService.getFoodById(id);
        return new FoodDto(foodDetail.id, foodDetail.brandName, foodDetail.name,
                foodDetail.carbohydrates, foodDetail.calories, foodDetail.proteins,
                foodDetail.electrolytes, foodDetail.link, foodDetail.comment, foodDetail.type);
    }

    @Override
    public Map<Long, FoodDto> getFoodsByIds(Collection<Long> ids) {
        Map<Long, FoodDetail> foods = this.foodApplicationService.getFoodsByIds(ids);
        return foods.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> {
            FoodDetail foodDetail = entry.getValue();
            return new FoodDto(foodDetail.id, foodDetail.brandName, foodDetail.name,
                    foodDetail.carbohydrates, foodDetail.calories, foodDetail.proteins,
                    foodDetail.electrolytes, foodDetail.link, foodDetail.comment, foodDetail.type);
        }));
    }
}
