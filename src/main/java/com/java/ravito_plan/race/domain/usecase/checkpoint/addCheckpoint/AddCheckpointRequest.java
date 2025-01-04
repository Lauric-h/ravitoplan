package com.java.ravito_plan.race.domain.usecase.checkpoint.addCheckpoint;

import com.java.ravito_plan.race.domain.dto.CheckpointParams;

public record AddCheckpointRequest(Long userId, Long raceId, CheckpointParams checkpointParams) {

}
