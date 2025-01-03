package com.java.ravito_plan.race.domain.usecase.checkpoint.addFoodToCheckpoint;

import com.java.ravito_plan.race.domain.dto.RaceFoodDto;
import com.java.ravito_plan.race.domain.model.Checkpoint;
import java.util.Map;

public record AddFoodToCheckpointResponse(Checkpoint checkpoint, Map<Long, RaceFoodDto> foods) {

}
