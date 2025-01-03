package com.java.ravito_plan.race.domain.usecase.checkpoint.deleteCheckpoint;

public record DeleteCheckpointRequest(Long raceId, Long checkpointId, Long userId) {

}
