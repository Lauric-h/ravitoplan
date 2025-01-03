package com.java.ravito_plan.race.domain.usecase.checkpoint.addCheckpoint;

import com.java.ravito_plan.race.application.dto.command.CreateCheckpointCommand;

public record AddCheckpointRequest(Long userId, Long raceId, CreateCheckpointCommand command) {

}
