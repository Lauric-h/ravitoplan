package com.java.ravito_plan.race.domain.usecase.checkpoint.removeFoodFromCheckpoint;

public record RemoveFoodFromCheckpointRequest(Long raceId, Long checkpointId, Long userId,
                                              Long foodId, int quantity) {

}
