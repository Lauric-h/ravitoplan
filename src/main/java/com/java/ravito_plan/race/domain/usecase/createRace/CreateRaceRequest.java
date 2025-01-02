package com.java.ravito_plan.race.domain.usecase.createRace;

import com.java.ravito_plan.race.application.dto.command.CreateRaceCommand;

public record CreateRaceRequest(Long userId, CreateRaceCommand command) {

}
