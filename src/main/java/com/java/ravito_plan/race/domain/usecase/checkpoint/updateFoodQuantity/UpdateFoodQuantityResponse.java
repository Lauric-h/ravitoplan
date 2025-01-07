package com.java.ravito_plan.race.domain.usecase.checkpoint.updateFoodQuantity;

import com.java.ravito_plan.race.domain.dto.RaceFoodDto;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import java.util.Map;

public record UpdateFoodQuantityResponse(Checkpoint checkpoint, Map<Long, RaceFoodDto> foods) {

}
