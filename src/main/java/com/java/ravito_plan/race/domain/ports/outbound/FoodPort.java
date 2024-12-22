package com.java.ravito_plan.race.domain.ports.outbound;

import com.java.ravito_plan.race.application.dto.internal.ExternalFoodDto;
import java.util.Collection;
import java.util.Map;

public interface FoodPort {

    ExternalFoodDto getFoodById(Long id);
    Map<Long, ExternalFoodDto> getFoodsByIds(Collection<Long> ids);
}
