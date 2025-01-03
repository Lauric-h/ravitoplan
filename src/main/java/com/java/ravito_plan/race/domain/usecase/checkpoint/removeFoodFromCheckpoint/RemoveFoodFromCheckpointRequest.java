package com.java.ravito_plan.race.domain.usecase.checkpoint.removeFoodFromCheckpoint;

import com.java.ravito_plan.race.application.dto.command.DeleteFoodCommand;

public record RemoveFoodFromCheckpointRequest(Long raceId, Long checkpointId, Long userId, Long foodId, DeleteFoodCommand command) {

}
