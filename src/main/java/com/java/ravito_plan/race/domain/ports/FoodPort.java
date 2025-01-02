package com.java.ravito_plan.race.domain.ports;

import com.java.ravito_plan.race.domain.dto.RaceFoodDto;
import java.util.Collection;
import java.util.Map;

public interface FoodPort {

    RaceFoodDto getFoodById(Long id);

    Map<Long, RaceFoodDto> getFoodsByIds(Collection<Long> ids);
}
