package com.java.ravito_plan.race.domain.usecase.checkpoint.addFoodToCheckpoint;

public record AddFoodToCheckpointRequest(Long raceId, Long checkpointId, Long userId, Long foodId,
                                         int quantity) {

}
