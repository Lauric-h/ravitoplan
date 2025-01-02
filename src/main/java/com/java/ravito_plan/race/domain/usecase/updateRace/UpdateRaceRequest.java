package com.java.ravito_plan.race.domain.usecase.updateRace;

import com.java.ravito_plan.race.application.dto.command.UpdateRaceCommand;

public record UpdateRaceRequest(UpdateRaceCommand command, Long userId) {

}
