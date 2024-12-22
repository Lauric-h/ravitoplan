package com.java.ravito_plan.race.domain.ports.outbound;

import com.java.ravito_plan.race.application.dto.internal.FoodDto;
import java.util.Collection;
import java.util.Map;

public interface FoodPort {

    FoodDto getFoodById(Long id);
    Map<Long, FoodDto> getFoodsByIds(Collection<Long> ids);
}
