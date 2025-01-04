package com.java.ravito_plan.race.domain.usecase.checkpoint.updateCheckpoint;

import com.java.ravito_plan.race.domain.dto.CheckpointParams;

public record UpdateCheckpointRequest(Long raceId, Long checkpointId, Long userId,
                                      CheckpointParams checkpointParams) {

}
