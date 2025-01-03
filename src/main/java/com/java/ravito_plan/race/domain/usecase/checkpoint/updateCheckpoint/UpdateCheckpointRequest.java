package com.java.ravito_plan.race.domain.usecase.checkpoint.updateCheckpoint;

import com.java.ravito_plan.race.application.dto.command.UpdateCheckpointCommand;

public record UpdateCheckpointRequest(Long raceId, Long checkpointId, Long userId, UpdateCheckpointCommand command) {

}
