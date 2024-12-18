package com.java.ravito_plan.race.domain.ports.outbound;

import com.java.ravito_plan.race.application.dto.ExternalFoodDto;

public interface FoodPort {

    ExternalFoodDto getFoodById(Long id);
}
