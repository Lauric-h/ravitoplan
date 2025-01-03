package com.java.ravito_plan.race.domain.usecase.checkpoint.addFoodToCheckpoint;

import com.java.ravito_plan.race.application.dto.command.AddFoodCommand;

public record AddFoodToCheckpointRequest(Long raceId, Long checkpointId, Long userId,
                                         AddFoodCommand command) {

}
